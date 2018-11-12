package ca.airspeed.wsbooks

class ItemSalesTax {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		isActive(nullable: false, inList: ['true', 'false'])
		itemDesc(nullable: true)
		isUsedOnPurchaseTransaction(nullable: false, inList: ['true', 'false'])
		taxRate(nullable: false)
		taxVendorRefListID(nullable: false)
		taxVendorRefFullName(nullable: true)
		customField1(nullable: true)
		customField2(nullable: true)
		customField3(nullable: true)
		customField4(nullable: true)
		customField5(nullable: true)
		customField6(nullable: true)
		customField7(nullable: true)
		customField8(nullable: true)
		customField9(nullable: true)
		customField10(nullable: true)
		status(nullable: false, inList: ['ADD', 'UPDATE', 'DELETE'])
	}

	static mapping = {
		datasource 'opensync'
		table 'itemsalestax'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		isActive column: 'IsActive'
		itemDesc column: 'ItemDesc'
		isUsedOnPurchaseTransaction column: 'IsUsedOnPurchaseTransaction'
		taxRate column: 'TaxRate'
		taxVendorRefListID column: 'TaxVendorRef_ListID'
		taxVendorRefFullName column: 'TaxVendorRef_FullName'
		customField1 column: 'CustomField1'
		customField2 column: 'CustomField2'
		customField3 column: 'CustomField3'
		customField4 column: 'CustomField4'
		customField5 column: 'CustomField5'
		customField6 column: 'CustomField6'
		customField7 column: 'CustomField7'
		customField8 column: 'CustomField8'
		customField9 column: 'CustomField9'
		customField10 column: 'CustomField10'
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
	String itemDesc
	String isUsedOnPurchaseTransaction
	String taxRate
	String taxVendorRefListID
	String taxVendorRefFullName
	String customField1
	String customField2
	String customField3
	String customField4
	String customField5
	String customField6
	String customField7
	String customField8
	String customField9
	String customField10
	String status

	def getListID() {
		return id
	}

	def setListID(String listID) {
		this.id = listID
	}

	String toString() {
		return name
	}
}
