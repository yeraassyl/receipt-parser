package receipt
import receipt.parser.ReceiptParser

import scala.collection.mutable
import java.io._

object Boot extends App {
  val pw = new PrintWriter(new File("receipt.json"))
  pw.write(ReceiptParser.toJsonString("raw.txt"))
  pw.close()
}
