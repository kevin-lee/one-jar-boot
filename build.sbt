
val projectDevelopers: List[Developer] = List(
  Developer(
    "Kevin-Lee",
    "Kevin Lee", "kevin.code@kevinlee.io",
    url("https://github.com/Kevin-Lee")
  ))

val projectHomePage: Option[sbt.URL] = Some(url("https://github.com/Kevin-Lee/one-jar-boot"))

val projectScmInfo: Option[ScmInfo] = Some(ScmInfo(
  url("https://github.com/Kevin-Lee/one-jar-boot"),
  "git@github.com:Kevin-Lee/one-jar-boot.git"
))


val junitJupiterVersion = "5.6.2"

val GitHubUsername = "Kevin-Lee"
val ProjectName = "one-jar-boot"

ThisBuild / scalaVersion := "2.13.5"
ThisBuild / organization := "io.kevinlee"

ThisBuild / developers := projectDevelopers
ThisBuild / homepage := projectHomePage
ThisBuild / scmInfo := projectScmInfo

lazy val oneJarBoot = (project in file("."))
  .enablePlugins(
    DevOopsJavaPlugin,
    DevOopsGitHubReleasePlugin,
  )
  .settings(
    name := ProjectName,
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
    licenses := List("MIT" -> url("http://opensource.org/licenses/MIT")),
    /* GitHub Release { */
    devOopsPackagedArtifacts := List(s"target/${name.value}*.jar"),
    /* } GitHub Release */


  )
