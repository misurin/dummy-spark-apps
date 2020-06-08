import sbt._

lazy val root = (project in file("."))
  .settings(resolvers ++= BuildSettings.resolvers)
  .settings(BuildSettings.settings: _*)
  .settings(BuildSettings.assemblyOptionsSettings: _*)
  .settings(addArtifact(artifact in (Compile, assembly), assembly))
  .settings(libraryDependencies ++= Dependencies.dataQaJars)
  .settings(dependencyOverrides ++= Dependencies.overrides)
  
