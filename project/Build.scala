import sbt._
import Keys._

import play.Project._

object ApplicationBuild extends Build {

    val appName         = "zentask"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      javaCore,
      javaJdbc,
      javaEbean,
      "org.apache.commons" % "commons-email" % "1.3.1"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      testOptions in Test ~= { args =>
  for {
    arg <- args
    val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
    val newArg = if(ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
  } yield newArg
}

    )    

}
            



