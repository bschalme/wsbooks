package ca.airspeed.wsbooks

import grails.rest.Resource

@Resource(uri='/TxnExpenseLineDetail', formats=['json', 'xml'])
class TxnExpenseLineDetail {

	static belongsTo = [creditCardCharge : CreditCardCharge]

	static constraints = {
		txnLineID(nullable: false)
		account(nullable: false)
		accountRefFullName(nullable: true)
		amount(nullable: false)
		memo(nullable: true)
		customer(nullable: true)
		customerRefFullName(nullable: true)
		classRefListID(nullable: true)
		classRefFullName(nullable: true)
		salesTaxCode(nullable: true)
		salesTaxCodeRefFullName(nullable: true)
		billableStatus(nullable: true)
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
		groupIDKEY(nullable: true)
	}

	static mapping = {
		datasource 'opensync'
		table 'TxnExpenseLineDetail'
		version false
		id column: 'TxnLineID', generator: 'assigned'
		txnLineID column: 'TxnLineID'
		account column: 'AccountRef_ListID'
		accountRefFullName column: 'AccountRef_FullName'
		amount column: 'Amount'
		memo column: 'Memo'
		customer column: 'CustomerRef_ListID'
		customerRefFullName column: 'CustomerRef_FullName'
		classRefListID column: 'ClassRef_ListID'
		classRefFullName column: 'ClassRef_FullName'
		salesTaxCode column: 'SalesTaxCodeRef_ListID'
		salesTaxCodeRefFullName column: 'SalesTaxCodeRef_FullName'
		billableStatus column: 'BillableStatus'
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
		groupIDKEY column: 'GroupIDKEY'
		creditCardCharge column: 'IDKEY'
	}

	String id

	static transients = ['txnLineID']
	String txnLineID
	Account account
	String accountRefFullName
	Float amount
	String memo
	Customer customer
	String customerRefFullName
	String classRefListID
	String classRefFullName
	SalesTaxCode salesTaxCode
	String salesTaxCodeRefFullName
	String billableStatus
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
	String groupIDKEY

	def getTxnLineID() {
		return id
	}

	def setTxnLineID(String txnId) {
		this.id = txnId
	}

	def setId(String id) {
		this.id = id
		this.txnLineID = id
	}
}
