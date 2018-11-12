package ca.airspeed.wsbooks

class TimeTracking {

	static constraints = {
		txnID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		txnNumber(nullable: true)
		txnDate(nullable: false)
		entityRefListID(nullable: false)
		entityRefFullName(nullable: true)
		customer(nullable: false)
		customerRefFullName(nullable: true)
		itemService(nullable: false)
		itemServiceRefFullName(nullable: true)
		rate(nullable: true)
		duration(nullable: false, blank: false, matches: "PT[0-9]{1,2}H[0-5]?[0-9]M")
		classRefListID(nullable: true)
		classRefFullName(nullable: true)
		payrollItemWageRefListID(nullable: true)
		payrollItemWageRefFullName(nullable: true)
		notes(nullable: true)
		isBillable(nullable: true)
		isBilled(nullable: true)
		billableStatus(nullable: true)
		status(nullable: false, inList: ['ADD', 'UPDATE', 'DELETE'])
//		customField1(nullable: true)
//		customField2(nullable: true)
//		customField3(nullable: true)
//		customField4(nullable: true)
//		customField5(nullable: true)
//		customField6(nullable: true)
//		customField7(nullable: true)
//		customField8(nullable: true)
	}

	static mapping = {
		datasource 'opensync'
		table 'timetracking'
		version false
		id column: 'TxnID', generator: 'assigned'
		txnID column: 'TxnID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		txnNumber column: 'TxnNumber'
		txnDate column: 'TxnDate'
		entityRefListID column: 'EntityRef_ListID'
		entityRefFullName column: 'EntityRef_FullName'
		customer column: 'CustomerRef_ListID'
		customerRefFullName column: 'CustomerRef_FullName'
		itemService column: 'ItemServiceRef_ListID'
		itemServiceRefFullName column: 'ItemServiceRef_FullName'
		rate column: 'Rate'
		duration column: 'Duration'
		classRefListID column: 'ClassRef_ListID'
		classRefFullName column: 'ClassRef_FullName'
		payrollItemWageRefListID column: 'PayrollItemWageRef_ListID'
		payrollItemWageRefFullName column: 'PayrollItemWageRef_FullName'
		notes column: 'Notes', type: 'text'
		isBillable column: 'IsBillable'
		isBilled column: 'IsBilled'
		billableStatus column: 'BillableStatus'
		status column: 'Status'
//		customField1 column: 'CustomField1'
//		customField2 column: 'CustomField2'
//		customField3 column: 'CustomField3'
//		customField4 column: 'CustomField4'
//		customField5 column: 'CustomField5'
//		customField6 column: 'CustomField6'
//		customField7 column: 'CustomField7'
//		customField8 column: 'CustomField8'
	}
	
	String id

	static transients = ['txnID']
	String txnID
	String timeCreated
	String timeModified
	String editSequence
	Integer txnNumber
	Date txnDate
	String entityRefListID
	String entityRefFullName
	Customer customer
	String customerRefFullName
	ItemService itemService
	String itemServiceRefFullName
	String rate
	String duration
	String classRefListID
	String classRefFullName
	String payrollItemWageRefListID
	String payrollItemWageRefFullName
	String notes
	String isBillable
	String isBilled
	String billableStatus
	String status
//	String customField1
//	String customField2
//	String customField3
//	String customField4
//	String customField5
//	String customField6
//	String customField7
//	String customField8

	def getTxnID() {
		return id
	}

	def setTxnID(String txnID) {
		this.id = txnID
	}
	
	def setDurationInMinutes(Integer minutes) {
		Integer hh = minutes / 60
		Integer mm = minutes % 60
		duration = 'PT' + hh + 'H' + mm + 'M'
	}
	
	def getDurationInMinutes() {
		def h = duration.indexOf('H')
		def hours = Integer.parseInt(duration.substring(2, h))
		def minutes = Integer.parseInt(duration.substring(h + 1, duration.length() - 1))
		return (hours * 60) + minutes
	}

	String toString() {
		return txnDate + ' -> ' + customerRefFullName + ' -> ' + duration 
	}
}
