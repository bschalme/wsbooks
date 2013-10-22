package ca.airspeed.wsbooks

class SalesOrPurchaseDetail {

	static constraints = {
		description(nullable: false)
		price(nullable: false)
		pricePercent(nullable: true)
		accountRefListID(nullable: false)
		accountRefFullName(nullable: true)
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
		iDKEY(nullable: false)
		groupIDKEY(nullable: true)
	}

	static mapping = {
		datasource 'opensync'
		table 'salesorpurchasedetail'
		version false
		id column: 'IDKEY', generator: 'assigned'
		description column: 'Description'
		price column: 'Price'
		pricePercent column: 'PricePercent'
		accountRefListID column: 'AccountRef_ListID'
		accountRefFullName column: 'AccountRef_FullName'
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
		iDKEY column: 'IDKEY'
		groupIDKEY column: 'GroupIDKEY'
	}

	String id

	static transients = ['iDKEY']
	String description
	String price
	String pricePercent
	String accountRefListID
	String accountRefFullName
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
	String iDKEY
	String groupIDKEY

	def getIDKEY() {
		return id
	}

	def setIDKEY(String idkey) {
		this.id = idkey
	}

	String toString() {
		return description
	}
}
