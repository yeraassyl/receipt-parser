package receipt.parser

import receipt.model.{Item, Receipt}
import spray.json.{JsValue, enrichAny}

import scala.util.matching.Regex
import receipt.serializer.ParserJsonProtocol._

object ReceiptParser extends Parser {
  val keys = Seq("Филиал", "БИН", "НДС Серия", "№", "Касса", "Смена", "Порядковый номер чека", "Чек", "Кассир")
  val itemsPattern: Regex = "([0-9]+\\.)\\n([0-9a-zA-Zа-яА-Я\\%,\\.\\-()\\[\\]№ ]+)\\n([0-9,x ]+)\\n([0-9,]+)".r

  override def parse(source: String): JsValue = {
    val args = patterGen.map(pattern => {
      pattern.findFirstMatchIn(source).get.group(2)
    })

    val items = itemsPattern.findAllMatchIn(source).map(patternMatch => {
      val str = patternMatch.group(3);
      Item(patternMatch.group(2), str.split(",000")(0).toInt,
        str.split("x ")(1).replace(",00", "").replace(" ", "").toInt,
        patternMatch.group(4).replace(",00", "").replace(" ", "").toInt)
    }).toList

    Receipt(items, args.head, args(1), args(2), args(3), args(4), args(5).toInt, args(6), args(7), args(8)).toJson
  }

  val patterGen: Seq[Regex] =
    for (key <- keys)
      yield s"((?<=^|\\s)[$key]+) ([0-9а-яА-Яa-zA-Z\\ -№]*)".r
}
