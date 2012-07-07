package bootstrap.liftweb

import app.util.Util
import net.liftweb.common.Full
import net.liftweb.http.LiftRulesMocker.toLiftRules
import net.liftweb.http.{XHtmlInHtml5OutProperties, ResourceServer, Req, LiftRules}
import net.liftweb.sitemap.LocPath.stringToLocPath
import net.liftweb.sitemap.{SiteMap, Menu}
import net.liftweb.util.Vendor.valToVender

/**
 * A class that's instantiated early and run.
 * It allows the application to modify lift's environment.
 */
class Boot {

  def boot {
    // where to search snippet
    LiftRules addToPackages "app"
    LiftRules.snippets.append(Util.snippetHandlers)

    // Build SiteMap
    def sitemap() = SiteMap(
      Menu.i("主页") / "index", // the simple way to declare a menu
      Menu.i("MVC") / "mvc", // the simple way to declare a menu
      Menu.i("Files") / "files",
      Menu.i("About") / "about")

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules setSiteMapFunc sitemap

    ResourceServer.allow {
      case "app" :: _  ⇒ true
      case "libs" :: _ ⇒ true
    }

    // Use jQuery 1.4
    //LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() ⇒ LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() ⇒ LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    //LiftRules.stripComments.default.set(() ⇒ false) //if (Props.devMode) false else true

    // Use HTML5 for rendering
    // LiftRules.htmlProperties.default.set { (r: Req) ⇒ new Html5Properties(r.userAgent) }
    LiftRules.htmlProperties.default.set((r: Req) ⇒ new XHtmlInHtml5OutProperties(r.userAgent))
  }
}
