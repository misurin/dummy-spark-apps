import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._
import sbtassembly.PathList

object BuildSettings {
  lazy val resolvers: Seq[MavenRepository] = Seq(
    Resolver.mavenLocal,
    "nexus" at "https://nexus.shopback-data.com/repository/maven-public/"
  )

  lazy val settings: Seq[Def.Setting[_]] = Seq(
    name := "dummy-spark-apps",
    scalaVersion := "2.11.8",
    version := "1.0-SNAPSHOT",
    fork in Test := true,
    organization := "com.shopback.data",
    publishTo := {
      val nexus = "https://nexus.shopback-data.com/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "repository/maven-internal-snapshots/")
      else
        Some("releases"  at nexus + "repository/maven-internal-releases/")
    },
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
  )

  lazy val assemblyOptionsSettings: Seq[Def.Setting[_]] = Seq(
    assemblyJarName in assembly := "dummy-spark-apps.jar",
    test in assembly := {},
    assemblyMergeStrategy in assembly := {
      case PathList("reference.conf")                                          => MergeStrategy.concat
      case PathList("org", "apache", "hadoop", a, xs @ _*) if a == "mapreduce" => MergeStrategy.discard
      case entry =>
        val strategy = (assemblyMergeStrategy in assembly).value(entry)
        if (strategy == MergeStrategy.deduplicate) MergeStrategy.first
        else strategy
    },
    artifact in (Compile, assembly) := {
      val art = (artifact in (Compile, assembly)).value
      art.withClassifier(Some("assembly"))
    }
  )
}
