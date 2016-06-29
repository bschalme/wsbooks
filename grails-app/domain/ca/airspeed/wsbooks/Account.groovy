package ca.airspeed.wsbooks

import com.sun.org.apache.bcel.internal.generic.RETURN;

class Account {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		fullName(nullable: false)
		isActive(nullable: false, inList: ['true', 'false'])
		parentRefListID(nullable: true)
		parentRefFullName(nullable: true)
		sublevel(nullable: true)
		accountType(nullable: false, inList: [
			'AccountsReceivable',
			'Bank',
			'CostOfGoodsSold',
			'CreditCard',
			'Equity',
			'Expense',
			'FixedAsset',
			'Income',
			'LongTermLiability',
			'NonPosting',
			'OtherAsset',
			'OtherCurrentAsset',
			'OtherCurrentLiability',
			'OtherExpense',
			'OtherIncome',
		])
		detailAccountType(nullable: true)
		accountNumber(nullable: true)
		bankNumber(nullable: true)
		lastCheckNumber(nullable: true)
		description(nullable: true)
		balance(nullable: true)
		totalBalance(nullable: true)
		cashFlowClassification(nullable: true)
		specialAccountType(nullable: true)
		salesTaxCodeRefListID(nullable: true)
		salesTaxCodeRefFullName(nullable: true)
		isTaxAccount(nullable: true)
		taxLineID(nullable: true)
		taxLineInfo(nullable: true)
		currencyRefListID(nullable: true)
		currencyRefFullName(nullable: true)
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
		status(nullable: false, inList: ['ADD'])
	}

	static mapping = {
		datasource 'opensync'
		table 'account'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		fullName column: 'FullName'
		isActive column: 'IsActive'
		parentRefListID column: 'ParentRef_ListID'
		parentRefFullName column: 'ParentRef_FullName'
		sublevel column: 'Sublevel'
		accountType column: 'AccountType'
		detailAccountType column: 'DetailAccountType'
		accountNumber column: 'AccountNumber'
		bankNumber column: 'BankNumber'
		lastCheckNumber column: 'LastCheckNumber'
		description column: 'Description'
		balance column: 'Balance'
		totalBalance column: 'TotalBalance'
		cashFlowClassification column: 'CashFlowClassification'
		specialAccountType column: 'SpecialAccountType'
		salesTaxCodeRefListID column: 'SalesTaxCodeRef_ListID'
		salesTaxCodeRefFullName column: 'SalesTaxCodeRef_FullName'
		isTaxAccount column: 'IsTaxAccount'
		taxLineID column: 'TaxLineID'
		taxLineInfo column: 'TaxLineInfo'
		currencyRefListID column: 'CurrencyRef_ListID'
		currencyRefFullName column: 'CurrencyRef_FullName'
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
	String fullName
	String isActive
	String parentRefListID
	String parentRefFullName
	Integer sublevel
	String accountType
	String detailAccountType
	String accountNumber
	String bankNumber
	String lastCheckNumber
	String description
	BigDecimal balance
	BigDecimal totalBalance
	String cashFlowClassification
	String specialAccountType
	String salesTaxCodeRefListID
	String salesTaxCodeRefFullName
	String isTaxAccount
	Integer taxLineID
	String taxLineInfo
	String currencyRefListID
	String currencyRefFullName
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

	def setListID(String listId) {
		this.id = listId
	}

	String toString() {
		return fullName
	}
}
