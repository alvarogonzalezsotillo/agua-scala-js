enablePlugins(ScalaJSPlugin)

name := "Ajustador de ecuacion"
scalaVersion := "2.12.4" // or any other Scala version >= 2.10.2

// This is an application with a main method
mainClass in  (Compile, run) := Some("tutorial.webapp.TutorialApp")
scalaJSUseMainModuleInitializer := true

// DOM DESDE SCALA
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"

// JQUERY
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.1"

// SCALATEST
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.0" % "test"


// USAR WEBJARS PARA JQUERY
skip in packageJSDependencies := false
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"


