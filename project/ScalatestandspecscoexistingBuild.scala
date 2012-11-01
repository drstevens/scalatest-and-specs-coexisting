import sbt._
import sbt.Keys._
import net.virtualvoid.sbt.graph.{Plugin => GraphPlugin}

object ScalatestandspecscoexistingBuild extends Build {

  lazy val specs2 = "org.specs2" %% "specs2" % "1.12.2" % "test"
  lazy val mockito = "org.mockito" % "mockito-core" % "1.9.0" % "test"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "1.8" % "test"

    lazy val scalatestandspecscoexisting = Project(
      id = "scalatest-and-specs-coexisting",
      base = file("."),
      settings = Project.defaultSettings ++
        GraphPlugin.graphSettings ++
        Seq(
        name := "Scalatest-And-Specs-Coexisting",
        organization := "com.bifflabs",
        version := "0.1",
        scalaVersion := "2.9.2",
  //      libraryDependencies ++= Seq(scalaTest, mockito)   //Tests Pass
  //      libraryDependencies ++= Seq(scalaTest, mockito, specs2)  //Tests Pass?!?
        libraryDependencies ++= Seq(scalaTest, specs2, mockito)  //Tests Fail
      )
    )
}
