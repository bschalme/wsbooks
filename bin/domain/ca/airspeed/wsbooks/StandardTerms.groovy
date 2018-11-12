package ca.airspeed.wsbooks

class StandardTerms {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		isActive(nullable: true)
		stdDueDays(nullable: false)
		stdDiscountDays(nullable: true)
		discountPct(nullable: true)
		status(nullable: false, inList: ['ADD', 'UPDATE', 'DELETE'])
	}

	static mapping = {
		datasource 'opensync'
		table 'standardterms'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		isActive column: 'IsActive'
		stdDueDays column: 'StdDueDays'
		stdDiscountDays column: 'StdDiscountDays'
		discountPct column: 'DiscountPct'
		status column: 'Status'
	}

	String id

	static transients = ['listID']
	String listID
	String timeCreated
	String timeModified
	String editSequence
	String name
	String isActive
	Integer stdDueDays
	Integer stdDiscountDays
	String discountPct
	String status

	def getListID() {
		return id
	}

	def setListID(String txnId) {
		this.id = txnId
	}

	String toString() {
		return name
	}
}
