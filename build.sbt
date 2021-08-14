lazy val props = new {
  val GitHubUsername = "Kevin-Lee"
  val ProjectName = "one-jar-boot"

  val projectDevelopers: List[Developer] = List(
    Developer(
      GitHubUsername,
      "Kevin Lee", "kevin.code@kevinlee.io",
      url(s"https://github.com/$GitHubUsername")
    ))

  val projectHomePage: Option[sbt.URL] = Some(url(s"https://github.com/$GitHubUsername/$ProjectName"))

  val projectScmInfo: Option[ScmInfo] = Some(ScmInfo(
    url(s"https://github.com/$GitHubUsername/$ProjectName"),
    s"git@github.com:$GitHubUsername/$ProjectName.git"
  ))
}
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / organization := "io.kevinlee"

ThisBuild / developers := props.projectDevelopers
ThisBuild / homepage := props.projectHomePage
ThisBuild / scmInfo := props.projectScmInfo

lazy val oneJarBoot = (project in file("."))
  .enablePlugins(
    DevOopsJavaPlugin,
    DevOopsGitHubReleasePlugin,
  )
  .settings(
    name := props.ProjectName,
    javacOptions := Seq(
      "-source",
      javaVersion.value,
      "-encoding",
      "UTF-8"
    ),
    Compile / compile / javacOptions ++= Seq(
      "-target",
      javaVersion.value,
      "-Xlint:unchecked",
      "-g",
      "-deprecation"
    ),
    publishMavenStyle := true,
    Test / publishArtifact := false,
    licenses := List("One-JAR" -> url(s"https://raw.githubusercontent.com/${props.GitHubUsername}/${props.ProjectName}/main/LICENSE")),
    Compile / unmanagedResources ++= {
      // TODO: Replace it with sbt-buildinfo once the version supporting Java class based buildinfo file is released.
      val projectVersion = version.value
      val log = sLog.value
      val versionFile = baseDirectory.value / ".version"
      val deleted = if (versionFile.exists()) versionFile.delete() else false
      if (deleted) {
        log.info(
          s""">> Writing version file: There had been the existing version file so it was removed to create a new one.
             |>> version=$projectVersion
             |""".stripMargin)
      } else {
        log.info(
          s""">> Writing version file: There's no version file found so it will create a new one.
             |>> version=$projectVersion
             |""".stripMargin)
      }
      IO.write(versionFile, projectVersion, java.nio.charset.StandardCharsets.UTF_8, append = false)
      List(baseDirectory.value / "LICENSE", versionFile)
    },
    /* GitHub Release { */
    devOopsPackagedArtifacts := List(s"target/${name.value}*.jar"),
    /* } GitHub Release */

  )
