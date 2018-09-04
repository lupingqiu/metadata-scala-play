name := """products"""
organization := "rube"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
resolvers += "cloudera" at "http://repository.cloudera.com/artifactory/cloudera-repos"

scalaVersion := "2.11.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies += "ws.securesocial" %% "securesocial" % "3.0-M8"

libraryDependencies += "org.apache.hive" % "hive-metastore" % "1.1.0-cdh5.5.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.0-cdh5.5.1"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "rube.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "rube.binders._"
