package receipt
import receipt.parser.ReceiptParser

import scala.collection.mutable

object Boot extends App {
  println(ReceiptParser.toJsonString("raw.txt"))
}
