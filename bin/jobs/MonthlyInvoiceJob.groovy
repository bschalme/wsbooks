import org.joda.time.DateTime;

import ca.airspeed.wsbooks.Customer;

class MonthlyInvoiceJob {
    static triggers = {}

	def invoiceService
	def freshbooksService
	def freshbooksApiConn

	def description = "Generate Monthly Invoices"

	def execute() {
         def activeCusts = Customer.findAllByIsActive("true")
		 def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		 activeCusts.each { cust ->
			 def inv = invoiceService.createTimeByCustomerAndDate(cust, endOfLastMonth)
			 if (inv?.invoiceNumber != null) {
				 def fbInv = freshbooksService.createInvoice(inv)
				 def fbInvId = freshbooksApiConn.createInvoice(fbInv)
				 log.debug("fbInvId = " + fbInvId)
				 freshbooksApiConn.sendInvoiceByEmail(fbInvId)
			 }
		 }
 
    }
}
