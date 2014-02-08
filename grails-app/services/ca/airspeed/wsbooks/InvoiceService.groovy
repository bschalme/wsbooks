package ca.airspeed.wsbooks

import java.awt.GraphicsConfiguration.DefaultBufferCapabilities;
import java.math.MathContext;
import java.text.NumberFormat;
import java.text.SimpleDateFormat
import java.util.logging.Logger;

import org.apache.commons.lang.NotImplementedException;
import org.joda.time.DateTime
import org.springframework.util.Assert;

import com.sun.org.apache.bcel.internal.generic.ISTORE;

class InvoiceService {
	
	RecurrenceService recurrenceService
	
	def getNextRefNumber() {
		def invoices = Invoice.list()
		def result = 0
		invoices.each { inv ->
			if (inv.refNumber.toInteger() > result) {
				result = inv.refNumber.toInteger()
			}
		}
		result++
		log.debug('Next RefNumber is ' + result + '.')
		return result.toString()
	}

    def createRecurringInvoice(String newRefNumber, Recurrence recurrence) {
        def baseInv = Invoice.findByRefNumber(recurrence.entityNumber)
		def newInv = new Invoice()
		newInv.properties = baseInv.properties
		newInv.detailLines = null
		newInv.txnID = UUID.randomUUID().toString()
		newInv.refNumber = newRefNumber
		newInv.id = newInv.txnID
		newInv.txnDate = recurrence.nextRunDate
		newInv.appliedAmount = 0
		newInv.balanceRemaining = newInv.subtotal + newInv.salesTaxTotal
		newInv.isPaid = false
		newInv.isToBePrinted = true
		def fromFmt = new SimpleDateFormat("MMM d")
		def toFmt = new SimpleDateFormat("dd, yyyy")
		newInv.other = fromFmt.format(newInv.txnDate) + ' - ' + toFmt.format(new DateTime(newInv.txnDate)
			.plusMonths(1).withDayOfMonth(1).minusDays(1).toDate())
		newInv.status = 'ADD'
		def baseDtls = baseInv.detailLines
		baseInv.detailLines.each {
			def newDtl = new InvoiceLineDetail()
			newDtl.properties = it.properties
			newDtl.txnLineID = UUID.randomUUID().toString()
			newDtl.id = newDtl.txnLineID
			newInv.addToDetailLines(newDtl)
		}
		newInv.save(failOnError: true, flush: true)
		
		def invTotal = newInv.subtotal + newInv.salesTaxTotal
		def nf = NumberFormat.getCurrencyInstance()
		log.info("Created Invoice #" + newInv.refNumber + " for " + newInv.customerRefFullName + ' for $' + nf.format(invTotal) + ".")
    }
	
	def createRecurringInvoice(Recurrence recurrence) {
		if (recurrence.entityName == 'Invoice') {
		    createRecurringInvoice(getNextRefNumber(), recurrence)
			recurrenceService.increment(recurrence)
		}
		else {
			log.debug("Discarded a " + recurrence.entityName + " Recurrence.")
		}
	}
	
	def createTimeByCustomerAndDate(Customer customer, Date invoiceDate) {
		Assert.notNull(customer, "'customer' must not be null")
		Assert.notNull(invoiceDate, "Need an invoiceDate")
		def resultInvoice = [:]
		def resultLines = []
		
		def timeEntries = TimeTracking.findAllByCustomerRefListIDAndBillableStatus(customer.id, 'Billable')
		if (timeEntries.size() == 0) {
			log.debug("No billable TimeTracking entries found for " + customer.fullName + ".")
			return resultInvoice
		}
		
		def inv = new Invoice()
		inv.txnID = UUID.randomUUID().toString()
		inv.refNumber = getNextRefNumber()
		inv.txnDate = invoiceDate
		inv.arAccount = Account.findByAccountType('AccountsReceivable')
		inv.templateRefListID = 'C0000-1078107584'
		inv.customerRefListID = customer.listID
		inv.termsRefListID = '20000-929918818'
		inv.customerMsgRefListID = '70000-951878262'
		inv.isToBePrinted = 'true'
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')
		def startOfPeriod = new DateTime(invoiceDate).withDayOfMonth(1).toDate()
		inv.other = startOfMonthFm.format(startOfPeriod) + ' - ' + endOfMonthFm.format(invoiceDate)
		inv.status = 'ADD'
		
		resultInvoice.invoiceNumber = inv.refNumber
		resultInvoice.sourceSystem = 'QuickBooks'
		// ToDo: Refactor, since this assumes the immediate parent is the top-level Customer:
		resultInvoice.clientID = Customer.get(inv.customerRefListID).parentRefListID
		resultInvoice.date = inv.txnDate
		resultInvoice.terms = StandardTerms.get(inv.termsRefListID)?.name
		resultInvoice.period = inv.other
		// ToDo: Make this into a configuration item:
		resultInvoice.notes = 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		
		def itemDurations = [:]
		timeEntries.each  { entry ->
			def i = itemDurations.get(entry.itemServiceRefListID)
			if (i) {
				log.debug('Existing InvoiceLineDetail')
				itemDurations[entry.itemServiceRefListID] += entry.durationInMinutes
			}
			else {
				log.debug('New InvoiceLineDetail')
				itemDurations[entry.itemServiceRefListID] = entry.durationInMinutes
			}
		}
		itemDurations.each { item ->
			def line = new InvoiceLineDetail()
			line.txnLineID = UUID.randomUUID().toString()
			line.itemRefListID = item.key
			line.quantity = item.value / 60
			inv.addToDetailLines(line)
			
			def resultLine = [:]
			resultLine.name = ItemService.get(line.itemRefListID)?.fullName
			SalesOrPurchaseDetail spd = SalesOrPurchaseDetail.get(line.itemRefListID)
			resultLine.description = spd?.description
			resultLine.quantity = item.value / 60
			resultLine.unitCost = spd?.price.toBigDecimal()
			log.debug('line.itemRefListID = ' + line.itemRefListID)
			log.debug('ItemService.salesTaxCodeRefListID = ' + ItemService.get(line.itemRefListID).salesTaxCodeRefListID)
			ItemSalesTax ist = ItemSalesTax.get(SalesTaxCode.get(ItemService.get(line.itemRefListID).salesTaxCodeRefListID).itemSalesTaxRefListID)
			def taxes = []
			def tax = [:]
			tax.name = ist?.name?.split()[0]
			tax.percent = ist?.taxRate.toBigDecimal()
			taxes << tax
			resultLine.taxes = taxes
			resultLines << resultLine
		}
		inv.save(failOnError: true)
		resultInvoice.lines = resultLines
		timeEntries.each {
			it.billableStatus = 'HasBeenBilled'
			if (it.status != 'ADD') {
				it.status = 'UPDATE'
			}
			it.save(failOnError: true)
		}
		
		log.info("Created Invoice #" + inv.refNumber + " for " + customer.fullName + ".")
		def qodbcDf = new SimpleDateFormat('yyyy-MM-dd')
		log.info("Run this update in QODBC: UPDATE TimeTracking SET BillableStatus = 'HasBeenBilled' WHERE CustomerRefListID = '" + customer.listID + "' AND TxnDate <= {d'" + qodbcDf.format(invoiceDate) + "'} AND BillableStatus = 'Billable'")
		
		return resultInvoice
	}
	
	def noOpTimeByCustomerAndDate(Customer customer, Date invoiceDate) {
		log.info("Simulated invoice for " + customer.fullName + " for " + invoiceDate)
	}
}

	