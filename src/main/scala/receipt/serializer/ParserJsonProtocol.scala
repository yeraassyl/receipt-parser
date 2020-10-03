package receipt.serializer

import receipt.model.{Item, Receipt}
import spray.json.DefaultJsonProtocol

object ParserJsonProtocol extends DefaultJsonProtocol{
  implicit val itemFormat= jsonFormat4(Item);
  implicit val receiptFormat = jsonFormat10(Receipt);
}
