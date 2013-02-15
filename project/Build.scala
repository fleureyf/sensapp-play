import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "sensapp-play"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    lessEntryPoints <<= (sourceDirectory in Compile)(base => (
    	(base/"assets"/"stylesheets"/"bootstrap.less") +++
    	(base/"assets"/"stylesheets"/"responsive.less")
    ))	
  )

}
