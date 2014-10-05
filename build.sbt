name := "Myrt"

organization := "momijikawa"

version := "0.1"

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Momijikawa Maven repository on GitHub" at "http://windymelt.github.io/repo/"

resolvers += "spray repo" at "http://repo.spray.io"

scalariformSettings

org.scalastyle.sbt.ScalastylePlugin.Settings

ScctPlugin.instrumentSettings

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.13" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.2.4",
  "com.typesafe.akka" %% "akka-agent" % "2.2.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.4",
  "commons-codec" % "commons-codec" % "1.9",
  "org.scalaz" %% "scalaz-core" % "7.0.0",
  "org.scalaz" %% "scalaz-effect" % "7.0.0",
  "org.scalaz" %% "scalaz-typelevel" % "7.0.0",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.0.0" % "test",
  "com.psyonik" %% "psyonik-upnp" % "0.0.1-SNAPSHOT",
  "org.pegdown" % "pegdown" % "1.0.2",
  "junit" % "junit" % "latest.integration" % "test",
  "org.mockito" % "mockito-all" % "1.9.5",
  "io.spray" % "spray-can" % "1.2.1",
  "io.spray" % "spray-http" % "1.2.1",
  "io.spray" % "spray-httpx" % "1.2.1",
  "io.spray" % "spray-io" % "1.2.1",
  "io.spray" % "spray-client" % "1.2.1",
  "io.spray" % "spray-routing" % "1.2.1",
  "io.spray" %%  "spray-json" % "1.2.6",
  "org.jvnet.mimepull" % "mimepull" % "i.9.4"
)

initialCommands := "import momijikawa.myrt._"

initialCommands in console := "import scalaz._, Scalaz._"

// Specify publish directory with your environment.

publishTo := Some(Resolver.file("myrt",file(Path.userHome.absolutePath+"/.m2/repository"))(Patterns(true, Resolver.mavenStyleBasePattern)))

testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "junitxml", "console")

parallelExecution in Test := false
