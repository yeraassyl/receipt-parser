package receipt.parser

import spray.json.JsValue

import scala.io.Source
trait Parser {
  def readFile(name: String): String = {
    val source = Source.fromURL(getClass.getResource("/" + name));
    source.getLines().reduce((line1, line2) => line1 + "\n" + line2)
  }

  def parse(source: String): JsValue

  def toJsonString(name: String): String ={
    this.parse(readFile(name)).toString()
  }
}
