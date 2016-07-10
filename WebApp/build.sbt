name := "WebApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"

enablePlugins(JettyPlugin)
