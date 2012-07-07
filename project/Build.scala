import sbt._
import Keys._

object Build extends Build {
  import Resolvers._
  import BuildSettings._
  import Tasks._
  import Dependencies._

  lazy val liftwebAppTemplate = Project(
    id = "liftweb_app_template",
    base = file("."),
    settings = buildSettings ++
      seq(com.github.siasia.WebPlugin.webSettings: _*) ++
      Seq(libraryDependencies := commonsDependencies ++ liftwebDependencies))

  object BuildSettings {
    val buildSettings = Defaults.defaultSettings ++ Seq(
      scalacOptions ++= Seq(
        "-deprecation",
        "-unchecked"),
      javaOptions += "-Xmx912m",
      javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.6", "-target", "1.6"))
  }

  object Dependencies {
    import Versions._
    val commonsDependencies = Seq(
      "org.eclipse.jetty" % "jetty-webapp" % jettyVersion  % "container", // For Jetty 8
      //"org.eclipse.jetty" % "jetty-webapp" % "7.5.4.v20111024" % "container",
      "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
      "ch.qos.logback" % "logback-classic" % "1.0.6" % "compile->default",
      "com.h2database" % "h2" % "1.3.167",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4" % "compile->default",
      "junit" % "junit" % "4.10" % "test->default")
    val liftwebDependencies = Seq(
      "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources (),
      "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default" withSources (),
      "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default" withSources ())
  }

  object Resolvers {
  }

  object Tasks {
    //empty now
  }

  object Versions {
    val liftVersion = "2.4"
    val jettyVersion = "8.0.4.v20111024"
  }
}
