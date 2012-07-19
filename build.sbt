seq(conscriptSettings :_*)

organization := "com.era7"

name := "md2slides"

version := "0.1.2"

publishTo := Some(Resolver.file("local era7 releases",  new File( "/home/eparejatobes/data/releases.era7.com" )) )

// scalaVersion := "2.9.2"

publishMavenStyle := true

crossScalaVersions := Seq("2.9.1", "2.9.2")

libraryDependencies += "com.github.scopt" %% "scopt" % "2.1.0"
