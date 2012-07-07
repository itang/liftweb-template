package app.snippet

import java.text.SimpleDateFormat
import java.util.Date

import net.liftweb.util.Helpers._

class Years {

  def render = "*" #> theYears

  val startYear = "2011"

  private def theYears: String = {
    val currYear = new SimpleDateFormat("yyyy").format(new Date)
    currYear match {
      case `startYear`               ⇒ currYear
      case _ if currYear > startYear ⇒ startYear + "-" + currYear
      case _                         ⇒ throw new RuntimeException("start year:%s, current year:%s, invalid!" format (startYear, currYear))
    }
  }
}