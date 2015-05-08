package ca.airspeed.wsbooks

import grails.rest.Resource

class CreditCardCharge {

	static hasMany = [expenseLines : TxnExpenseLineDetail]

	static constraints = {
		txnID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		txnNumber(nullable: true)
		account(nullable: false)
		accountRefFullName(nullable: true)
		payeeEntity(nullable: false)
		payeeEntityRefFullName(nullable: true)
		txnDate(nullable: false)
		amount(nullable: true)
		currencyRefListID(nullable: true)
		currencyRefFullName(nullable: true)
		exchangeRate(nullable: true)
		amountInHomeCurrency(nullable: true)
		refNumber(nullable: true)
		memo(nullable: true)
		isTaxIncluded(nullable: true)
		salesTaxCode(nullable: true)
		salesTaxCodeRefFullName(nullable: true)
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
		status(nullable: false, inList: ['ADD', 'UPDATE'])
	}

	static mapping = {
		datasource 'opensync'
		table 'CreditCardCharge'
		version false
		id column: 'TxnID', generator: 'assigned'
		txnID column: 'TxnID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		txnNumber column: 'TxnNumber'
		account column: 'AccountRef_ListID'
		accountRefFullName column: 'AccountRef_FullName'
		payeeEntity column: 'PayeeEntityRef_ListID'
		payeeEntityRefFullName column: 'PayeeEntityRef_FullName'
		txnDate column: 'TxnDate'
		amount column: 'Amount'
		currencyRefListID column: 'CurrencyRef_ListID'
		currencyRefFullName column: 'CurrencyRef_FullName'
		exchangeRate column: 'ExchangeRate'
		amountInHomeCurrency column: 'AmountInHomeCurrency'
		refNumber column: 'RefNumber'
		memo column: 'Memo'
		isTaxIncluded column: 'IsTaxIncluded'
		salesTaxCode column: 'SalesTaxCodeRef_ListID'
		salesTaxCodeRefFullName column: 'SalesTaxCodeRef_FullName'
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

	static transients = ['txnID']
	String txnID
	String timeCreated
	String timeModified
	String editSequence
	Integer txnNumber
	Account account
	String accountRefFullName
	Vendor payeeEntity
	String payeeEntityRefFullName
	Date txnDate
	Float amount
	String currencyRefListID
	String currencyRefFullName
	Float exchangeRate
	Float amountInHomeCurrency
	String refNumber
	String memo
	String isTaxIncluded
	SalesTaxCode salesTaxCode
	String salesTaxCodeRefFullName
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

	def getTxnID() {
		return id
	}

	def setTxnID(String txnId) {
		this.id = txnId
		this.txnID = txnId
	}

	def setId(String id) {
		this.id = id
		this.txnID = id
	}

	String toString() {
		return payeeEntity.name
	}
}
