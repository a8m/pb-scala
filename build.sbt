name := "pb"
version := "0.1"
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "2.2.0",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "jline" % "jline" % "2.13"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
homepage := Some(url("http://github.com/a8m/pb-scala"))
