import sbt._

import sbtcrossproject.{CrossType, Platform}

object CompatCrossType extends CrossType {

  val compatPlatformId = "foo"

  @deprecated("use projectDir(crossBase: File, platform: Platform): File", "0.1.0")
  def projectDir(crossBase: File, projectType: String): File =
    if (projectType == compatPlatformId) crossBase
    else crossBase / ("." + projectType)

  def projectDir(crossBase: File, platform: Platform): File = {
    println(s"COMPATCROSSTYPE projectDir crossBase: $crossBase platform: $platform")
    val ret = if (platform.identifier == compatPlatformId) crossBase
    else crossBase / ("." + platform.identifier)
    println(s"COMPATCROSSTYPE ret: $ret")
    ret
  }

  def sharedSrcDir(projectBase: File, conf: String): Option[File] = {
    println(s"SHAREDSRCDIR projectBase: $projectBase platform: $conf")
    val ret = Some(projectBase.getParentFile / "src" / conf / "scala")
    println(s"SHAREDSRCDIR ret: $ret")
    ret
  }
}

