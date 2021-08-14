logLevel := Level.Warn

addSbtPlugin("com.geirsson" % "sbt-ci-release" % "1.5.7")

val sbtDevOopsVersion = "2.6.0"
addSbtPlugin("io.kevinlee" % "sbt-devoops-java"      % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-sbt-extra" % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-github"    % sbtDevOopsVersion)
