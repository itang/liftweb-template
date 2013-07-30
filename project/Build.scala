import sbt._
import Keys._

object Build extends Build {

  import Resolvers._
  import BuildSettings._
  import Tasks._
  import Dependencies._
  import Versions._

  lazy val liftwebAppTemplate = Project(
    id = "liftweb_app_template",
    base = file("."),
    settings = buildSettings ++
      seq(com.earldouglas.xsbtwebplugin.WebPlugin.webSettings: _*) ++
      Seq(libraryDependencies := commonsDependencies ++ liftwebDependencies))

  object BuildSettings {
    val buildSettings = Defaults.defaultSettings ++ Seq(
      scalaVersion := scalaBuildVersion,
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked"),
      javaOptions += "-Xmx1g",
      javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.6", "-target", "1.6"))
  }

  object Dependencies {
    val commonsDependencies = Seq(
      "org.eclipse.jetty" % "jetty-webapp" % jettyVersion  % "container", // For Jetty 8
      //"org.eclipse.jetty" % "jetty-webapp" % "7.5.4.v20111024" % "container",
      "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
      "ch.qos.logback" % "logback-classic" % logbackVersion % "compile->default",
      "com.h2database" % "h2" % h2Version,
      "org.postgresql" % "postgresql" % postgresqlVersion  % "compile->default",
      "junit" % "junit" % "4.10" % "test->default")
    val liftwebDependencies = Seq(
      "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources (),
      "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default" withSources (),
      "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default" withSources ())
  }

  object Resolvers {
  }

  object Tasks {
  }

  object Versions {
    val scalaBuildVersion = "2.10.2"
    val liftVersion = "2.5.1"
    val postgresqlVersion = "9.2-1003-jdbc4"
    val h2Version = "1.3.173"
    val logbackVersion = "1.0.13"
    val jettyVersion = "8.1.11.v20130520"
  }
}
