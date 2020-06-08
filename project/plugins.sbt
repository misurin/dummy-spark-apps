val assemblyVersion = "0.14.4"
val depGraphVersion = "0.9.0"
val scalaStylePlugin = "1.0.0"

logLevel := Level.Warn

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % assemblyVersion)

addSbtPlugin("org.scalastyle" % "scalastyle-sbt-plugin" % scalaStylePlugin)

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % depGraphVersion)
