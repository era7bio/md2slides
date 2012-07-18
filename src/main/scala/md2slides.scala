package com.era7

import scala.sys.process._

/** The launched conscript entry point */
class md2slides extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    Exit(md2slides.run(config.arguments))
  }
}

object md2slides {
  /** Shared by the launched version and the runnable version,
   * returns the process status code */
  def run(args: Array[String]): Int = {

    val parser = new scopt.immutable.OptionParser[md2slidesConf]("md2slides", "0.1.2") {

      def options = Seq(
        opt("o", "output", "output file") { 
          (v: String, c: md2slidesConf) => c.copy(output = v)
        },
        opt("c", "color", "color scheme: dark or white. Defaults to dark" ) {
          (v: String, c: md2slidesConf) => c.copy(color = v)
        },
        intOpt("l", "slide-level", "title level at which new slides start; defaults to 1, so that only h1 will make a new slide") {
          (v: Int, c: md2slidesConf) => c.copy(slideLevel = v)
        },
        intOpt("s", "tab-stop", "number of spaces considered a tab stop; defaults to 2") {
          (v: Int, c: md2slidesConf) => c.copy(tabStop = v)
        },
        arg("<file>", "markdown input file") {
          (v: String, c: md2slidesConf) => c.copy(input = v)
        }
      )
    }

    parser.parse(args, md2slidesConf()) map { config =>

      CmdFromConf(config).!
    } getOrElse {

      1
    } 
  }

  /** Standard runnable class entrypoint */
  def main(args: Array[String]) {
    System.exit(run(args))
  }
}

case class Exit(val code: Int) extends xsbti.Exit



case class md2slidesConf(
                          input: String = "",
                          output: String =  "md2slidesOutput.html",
                          color: String = "dark",
                          tabStop: Int = 2,
                          slideLevel: Int = 1
                        )

object CmdFromConf {

  def template(conf: md2slidesConf) = if (conf.color == "white") "slidesWhite.html" else "slides.html" 

  // pandoc --from=markdown --template=slidesWhite.html -t dzslides --smart --tab-stop=2 --highlight-style pygments --slide-level=1 -o toolsWhite.html tools.md
  def apply(conf: md2slidesConf) = Seq(
                                        "pandoc",
                                        "--from=markdown",
                                        "-t", "dzslides",
                                        "--smart",
                                        "--tab-stop=2",
                                        "--template="+ template(conf),
                                        "--slide-level="+ conf.slideLevel.toString,
                                        "--output="+ conf.output,
                                        conf.input
                                      )
}
