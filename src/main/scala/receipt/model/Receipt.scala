package receipt.model

case class Receipt(items: List[Item], companyName: String, bin: String, ndc: String, number: String, cashier: String, shiftNum: Int,
                   receiptOrderNum: String, receiptId: String, cashierName: String)