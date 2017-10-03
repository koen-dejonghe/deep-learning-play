name := """deep-learning-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .dependsOn(dlsProject)

// lazy val dlsProject = RootProject(
//  uri("https://github.com/koen-dejonghe/deep-learning-scala.git"))

lazy val dlsProject = RootProject(file("../deep-learning-scala"))

scalaVersion := "2.11.11"

libraryDependencies += jdbc
libraryDependencies += cache
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

def stagingClean = Command.command("clean-stage") { state =>
  val homeDir = sys.env("HOME")
  val k = ("rm -rf " + homeDir + "/.sbt/0.13/staging/").!
  state
}
commands ++= Seq(stagingClean)
