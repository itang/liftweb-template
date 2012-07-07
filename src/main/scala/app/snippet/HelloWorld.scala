package app.snippet

import java.util.Date

import app.lib.DependencyFactory
import net.liftweb.common.Box
import net.liftweb.util.Helpers._
import net.liftweb.util.IterableConst.boxString

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString + "-7")

  def render = "*" #> "Hello, Liftweb!"

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}
