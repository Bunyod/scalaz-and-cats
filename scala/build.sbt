name := """scalaz-vs-cats"""

version := "1.0"

scalaVersion in ThisBuild := "2.12.3"

/* Settings common to each sub project */
val common = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-language:higherKinds",
    "-Ypartial-unification"
  ),

  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core"     % "1.0.0-MF",
    "org.typelevel" %% "cats-effect"   % "0.4",
    "org.scalaz"    %% "scalaz-core"   % "7.2.15",
    "org.scalaz"    %% "scalaz-effect" % "7.2.15",
    "com.chuusai"   %% "shapeless"     % "2.3.2"
  )
)

/* The library itself */
lazy val lib = project.in(file(".")).settings(common)

/* Benchmarking suite.
 * Benchmarks can be executed by first switching to the `bench` project and then by running:
 *     jmh:run -t 1 -f 1 -wi 3 -i 3 .*Bench.*
 */
lazy val bench = project.in(file("bench")).settings(common).dependsOn(lib).enablePlugins(JmhPlugin)
