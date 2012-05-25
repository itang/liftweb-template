package app.util

import scala.xml.Unparsed
import scala.xml.NodeSeq
import net.liftweb.http.S
import net.liftweb.http.LiftRules.SnippetPF

object Util {

  def snippetHandlers: SnippetPF = {
    case List("ie-conditional-comment") ⇒ ieConditionalComment _
  }

  def ieConditionalComment(xhtml: NodeSeq) = {
    val ieVersion =
      (for (version ← S.attr("version")) yield version) openOr "IE"
    Unparsed("<!--[if " + ieVersion + "]>") ++ xhtml ++
      Unparsed("<![endif]-->")
  }
} 