name := "AdventOfCode"

version := "0.1"

scalaVersion := "3.1.0"

javacOptions ++= Seq("-Xms8G", "-Xmx8G", "-XX:+UseG1GC")

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.7"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test
libraryDependencies += "ch.obermuhlner" % "big-math" % "2.3.0"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
libraryDependencies += "org.typelevel" %% "cats-laws" % "2.7.0" % Test
libraryDependencies += "org.typelevel" %% "discipline-scalatest" % "2.1.5" % Test
libraryDependencies += "eu.throup" %% "circular" % "0.1.0-SNAPSHOT"
