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
			newDtl.item = it.item
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
		
		def timeEntries = TimeTracking.findAllByCustomerAndBillableStatus(customer, 'Billable')
		if (timeEntries.size() == 0) {
			log.debug("No billable TimeTracking entries found for " + customer.fullName + ".")
			return resultInvoice
		}
		
		def inv = new Invoice()
		inv.txnID = UUID.randomUUID().toString()
		inv.refNumber = getNextRefNumber()
		inv.txnDate = invoiceDate
		// ToDo: Make these configurable items:
		inv.arAccount = Account.get('3F0000-930012744')
		inv.template = Template.get('C0000-1078107584')
		inv.terms = StandardTerms.get('20000-929918818')
		inv.customerMsg = CustomerMsg.get('70000-951878262')
		inv.customer = customer
		inv.isToBePrinted = 'false'
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')
		def startOfPeriod = new DateTime(invoiceDate).withDayOfMonth(1).toDate()
		inv.other = startOfMonthFm.format(startOfPeriod) + ' - ' + endOfMonthFm.format(invoiceDate)
		inv.status = 'ADD'
		
		resultInvoice.invoiceNumber = inv.refNumber
		resultInvoice.sourceSystem = 'QuickBooks'
		// ToDo: Refactor, since this assumes the immediate parent is the top-level Customer:
		resultInvoice.clientID = inv.customer.parent?.listID
		resultInvoice.date = inv.txnDate
		resultInvoice.terms = inv.terms.name
		resultInvoice.period = inv.other
		// ToDo: Make this into a configuration item:
		resultInvoice.notes = 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		
		def itemDurations = [:]
		timeEntries.each  { entry ->
			def i = itemDurations.get(entry.itemService.listID)
			if (i) {
				log.debug('Existing InvoiceLineDetail')
				itemDurations[entry.itemService.listID] += entry.durationInMinutes
			}
			else {
				log.debug('New InvoiceLineDetail')
				itemDurations[entry.itemService.listID] = entry.durationInMinutes
			}
		}
		itemDurations.each { item ->
			def line = new InvoiceLineDetail()
			line.txnLineID = UUID.randomUUID().toString()
			line.item = Items.get(item.key)
			line.quantity = item.value / 60
			inv.addToDetailLines(line)
			
			def resultLine = [:]
			resultLine.name = ItemService.get(line.item.listID)?.fullName
			SalesOrPurchaseDetail spd = SalesOrPurchaseDetail.get(line.item.listID)
			resultLine.description = spd?.description
			resultLine.quantity = item.value / 60
			resultLine.unitCost = spd?.price.toBigDecimal()
			log.debug('line.item.listID = ' + line.item.listID)
			log.debug('ItemService.salesTaxCodeRefListID = ' + ItemService.get(line.item.listID).salesTaxCodeRefListID)
			ItemSalesTax ist = ItemSalesTax.get(SalesTaxCode.get(ItemService.get(line.item.listID).salesTaxCodeRefListID).itemSalesTaxRefListID)
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

	