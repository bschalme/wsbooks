package ca.airspeed.wsbooks

import static org.apache.commons.lang.StringUtils.*

class InvoiceLineDetail {

	static constraints = {
		txnLineID(nullable: false)
		itemRefListID(nullable: true)
		itemRefFullName(nullable: true)
		itemRefListID validator: {val, obj ->
			if (isBlank(val) && isBlank(obj.itemRefFullName)) return ['missing.item']
		}
		description(nullable: true)
		quantity(nullable: false)
		quantity validator: { val, obj ->
			if(!val.isNumber()) return 'notANumber' 
			if (val.toFloat() < 0.01F) return 'min.notmet'
		}
		unitOfMeasure(nullable: true)
		overrideUOMSetRefListID(nullable: true)
		overrideUOMSetRefFullName(nullable: true)
		rate(nullable: true)
		ratePercent(nullable: true)
		classRefListID(nullable: true)
		classRefFullName(nullable: true)
		amount(nullable: true)
		inventorySiteRefListID(nullable: true)
		inventorySiteRefFullName(nullable: true)
		serviceDate(nullable: true)
		salesTaxCodeRefListID(nullable: true)
		salesTaxCodeRefFullName(nullable: true)
		other1(nullable: true)
		other2(nullable: true)
		linkedTxnID(nullable: true)
		linkedTxnLineID(nullable: true)
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
		table 'invoicelinedetail'
		version false
		id column: 'TxnLineID', generator: 'assigned'
		txnLineID column: 'TxnLineID'
		itemRefListID column: 'ItemRef_ListID'
		itemRefFullName column: 'ItemRef_FullName'
		description column: 'Description'
		quantity column: 'Quantity'
		unitOfMeasure column: 'UnitOfMeasure'
		overrideUOMSetRefListID column: 'OverrideUOMSetRef_ListID'
		overrideUOMSetRefFullName column: 'OverrideUOMSetRef_FullName'
		rate column: 'Rate'
		ratePercent column: 'RatePercent'
		classRefListID column: 'ClassRef_ListID'
		classRefFullName column: 'ClassRef_FullName'
		amount column: 'Amount'
		inventorySiteRefListID column: 'InventorySiteRef_ListID'
		inventorySiteRefFullName column: 'InventorySiteRef_FullName'
		serviceDate column: 'ServiceDate'
		salesTaxCodeRefListID column: 'SalesTaxCodeRef_ListID'
		salesTaxCodeRefFullName column: 'SalesTaxCodeRef_FullName'
		other1 column: 'Other1'
		other2 column: 'Other2'
		linkedTxnID column: 'LinkedTxnID'
		linkedTxnLineID column: 'LinkedTxnLineID'
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

	static transients = ['txnLineID']
	String txnLineID
	String itemRefListID
	String itemRefFullName
	String description
	String quantity
	String unitOfMeasure
	String overrideUOMSetRefListID
	String overrideUOMSetRefFullName
	String rate
	String ratePercent
	String classRefListID
	String classRefFullName
	Float amount
	String inventorySiteRefListID
	String inventorySiteRefFullName
	Date serviceDate
	String salesTaxCodeRefListID
	String salesTaxCodeRefFullName
	String other1
	String other2
	String linkedTxnID
	String linkedTxnLineID
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

	def getTxnLineID() {
		return id
	}

	def setTxnLineID(String txnId) {
		this.id = txnId
	}
}
