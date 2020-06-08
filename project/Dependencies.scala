import sbt._

object Dependencies {

  object Versions {
    val Spark = "2.3.1"
    val Hadoop = "2.9.2"
  }

  private val sparkJars = Seq("spark-core", "spark-sql", "spark-hive")
    .map("org.apache.spark" %% _)
    .map(_ % Versions.Spark)
//    .map(_ % Provided)
    .map(_ excludeAll ExclusionRule("org.apache.hadoop"))

  private val hadoopJars = Seq("hadoop-aws", "hadoop-common", "hadoop-client")
    .map("org.apache.hadoop" % _)
    .map(_ % Versions.Hadoop)
//    .map(_ % Provided)
    .map(
      _ exclude ("javax.servlet", "servlet-api")
      exclude ("javax.servlet.jsp", "jsp-api")
      exclude ("org.mortbay.jetty", "servlet-api")
    )

  val dataQaJars: Seq[ModuleID] = sparkJars ++ hadoopJars 

  val overrides: Set[ModuleID] = Set(
    "com.fasterxml.jackson.core" % "jackson-core" % "2.9.6",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.6"
  )

}
