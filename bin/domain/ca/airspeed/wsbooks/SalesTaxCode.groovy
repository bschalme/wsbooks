package ca.airspeed.wsbooks

class SalesTaxCode {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		isActive(nullable: false, inList: ['true', 'false'])
		isTaxable(nullable: false, inList: ['true', 'false'])
		description(nullable: false)
		itemPurchaseTaxRefListID(nullable: false)
		itemPurchaseTaxRefFullName(nullable: true)
		itemSalesTaxRefListID(nullable: false)
		itemSalesTaxRefFullName(nullable: true)
		status(nullable: false, inList: ['ADD', 'UPDATE', 'DELETE'])
	}

	static mapping = {
		datasource 'opensync'
		table 'salestaxcode'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		isActive column: 'IsActive'
		isTaxable column: 'IsTaxable'
		description column: 'Description'
		itemPurchaseTaxRefListID column: 'ItemPurchaseTaxRef_ListID'
		itemPurchaseTaxRefFullName column: 'ItemPurchaseTaxRef_FullName'
		itemSalesTaxRefListID column: 'ItemSalesTaxRef_ListID'
		itemSalesTaxRefFullName column: 'ItemSalesTaxRef_FullName'
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
	String isTaxable
	String description
	String itemPurchaseTaxRefListID
	String itemPurchaseTaxRefFullName
	String itemSalesTaxRefListID
	String itemSalesTaxRefFullName
	String status

	def getListID() {
		return id
	}

	def setListID(String listID) {
		this.id = listID
	}

	String toString() {
		return description
	}
}
