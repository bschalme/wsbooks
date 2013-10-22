package ca.airspeed.wsbooks

import java.util.jar.Attributes.Name;

import com.sun.org.glassfish.external.arc.Taxonomy;

class FreshbooksService {
	def clients = ['CB0000-1190817876':2, '8000011D-1342792216':3]
	def items = ['A&P:$93.50/hr':'A&P-1', 'A&P:$100.00/hr':'A&P-1']

    def createInvoice(Map<Object, Object> inv) {
		def fbInv = new com.freshbooks.model.Invoice()
		fbInv.number = inv.invoiceNumber
		fbInv.clientId = clients[inv.clientID]
		fbInv.status = 'draft'
		fbInv.date = inv.date
		fbInv.notes = 'For services for the period ' + inv.period + '\n' + inv.notes
		fbInv.terms = inv.terms
		fbInv.currencyCode = 'CAD'
		def fbLines = []
		def fbLine = new com.freshbooks.model.InvoiceLine()
		inv.lines.each {
			fbLine.name = items[it.name]
			fbLine.description = it.description
			fbLine.quantity = it.quantity
			fbLine.unitCost = it.unitCost
			// ToDo: Loop through taxes or something more robust:
			fbLine.tax1Name = it.taxes?.get(0)?.name
			fbLine.tax1Percent = it.taxes?.get(0)?.percent.toString()
			fbLines << fbLine
		}
		fbInv.lines = fbLines
		
		return fbInv
    }
}
