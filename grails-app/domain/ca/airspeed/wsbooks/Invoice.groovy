package ca.airspeed.wsbooks

import static org.apache.commons.lang.StringUtils.*

import com.sun.org.apache.bcel.internal.generic.RETURN;

class Invoice {
	
	static hasMany = [detailLines : InvoiceLineDetail]

	static constraints = {
		txnID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		txnNumber(nullable: true)
		customerRefListID(nullable: true)
		customerRefFullName(nullable: true)
		customerRefListID validator: {val, obj ->
			if (isBlank(val) && isBlank(obj.customerRefFullName)) return ['missing.customer']
		}
		classRefListID(nullable: true)
		classRefFullName(nullable: true)
		arAccount(nullable: false, validator: {acct, obj, errors ->
			if (acct.accountType != 'AccountsReceivable') {
				errors.rejectValue('arAccount', 'account.must.be.ar', 'arAccount must be an AccountsReceivable account.')
				return false
			}
		})
		aRAccountRefFullName(nullable: true)
		templateRefListID(nullable: true)
		templateRefFullName(nullable: true)
		txnDate(nullable: false)
		refNumber(nullable: false)
		billAddressAddr1(nullable: true)
		billAddressAddr2(nullable: true)
		billAddressAddr3(nullable: true)
		billAddressAddr4(nullable: true)
		billAddressAddr5(nullable: true)
		billAddressCity(nullable: true)
		billAddressState(nullable: true)
		billAddressPostalCode(nullable: true)
		billAddressCountry(nullable: true)
		billAddressNote(nullable: true)
		shipAddressAddr1(nullable: true)
		shipAddressAddr2(nullable: true)
		shipAddressAddr3(nullable: true)
		shipAddressAddr4(nullable: true)
		shipAddressAddr5(nullable: true)
		shipAddressCity(nullable: true)
		shipAddressState(nullable: true)
		shipAddressPostalCode(nullable: true)
		shipAddressCountry(nullable: true)
		shipAddressNote(nullable: true)
		isPending(nullable: true)
		isFinanceCharge(nullable: true)
		pONumber(nullable: true)
		termsRefListID(nullable: true)
		termsRefFullName(nullable: true)
		termsRefListID validator: {val, obj ->
			if (isBlank(val) && isBlank(obj.termsRefFullName)) return ['missing.terms']
		}
		dueDate(nullable: true)
		salesRepRefListID(nullable: true)
		salesRepRefFullName(nullable: true)
		fOB(nullable: true)
		shipDate(nullable: true)
		shipMethodRefListID(nullable: true)
		shipMethodRefFullName(nullable: true)
		subtotal(nullable: true)
		itemSalesTaxRefListID(nullable: true)
		itemSalesTaxRefFullName(nullable: true)
		salesTaxPercentage(nullable: true)
		salesTaxTotal(nullable: true)
		appliedAmount(nullable: true)
		balanceRemaining(nullable: true)
		currencyRefListID(nullable: true)
		currencyRefFullName(nullable: true)
		exchangeRate(nullable: true)
		balanceRemainingInHomeCurrency(nullable: true)
		memo(nullable: true)
		isPaid(nullable: true)
		customerMsgRefListID(nullable: true)
		customerMsgRefFullName(nullable: true)
		isToBePrinted(nullable: true)
		isToBeEmailed(nullable: true)
		isTaxIncluded(nullable: true)
		customerSalesTaxCodeRefListID(nullable: true)
		customerSalesTaxCodeRefFullName(nullable: true)
		suggestedDiscountAmount(nullable: true)
		suggestedDiscountDate(nullable: true)
		other(nullable: true)
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
		detailLines(validator: { detailLines, invoice, errors ->
			if (detailLines == null) {
				errors.rejectValue('detailLines', 'nullable', 'detailLines cannot be null.')
				return false
			}
			if (detailLines.size() < 1) {
				errors.rejectValue('detailLines', 'invoice.details.minimum', 'Need at least one invoice detail line.')
				return false
			}
			return true
		})
	}

	static mapping = {
		datasource 'opensync'
		table 'invoice'
		version false
		id column: 'TxnID', generator: 'assigned'
		txnID column: 'TxnID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		txnNumber column: 'TxnNumber'
		customerRefListID column: 'CustomerRef_ListID'
		customerRefFullName column: 'CustomerRef_FullName'
		classRefListID column: 'ClassRef_ListID'
		classRefFullName column: 'ClassRef_FullName'
		aRAccountRefFullName column: 'ARAccountRef_FullName'
		templateRefListID column: 'TemplateRef_ListID'
		templateRefFullName column: 'TemplateRef_FullName'
		txnDate column: 'TxnDate'
		refNumber column: 'RefNumber'
		billAddressAddr1 column: 'BillAddress_Addr1'
		billAddressAddr2 column: 'BillAddress_Addr2'
		billAddressAddr3 column: 'BillAddress_Addr3'
		billAddressAddr4 column: 'BillAddress_Addr4'
		billAddressAddr5 column: 'BillAddress_Addr5'
		billAddressCity column: 'BillAddress_City'
		billAddressState column: 'BillAddress_State'
		billAddressPostalCode column: 'BillAddress_PostalCode'
		billAddressCountry column: 'BillAddress_Country'
		billAddressNote column: 'BillAddress_Note'
		shipAddressAddr1 column: 'ShipAddress_Addr1'
		shipAddressAddr2 column: 'ShipAddress_Addr2'
		shipAddressAddr3 column: 'ShipAddress_Addr3'
		shipAddressAddr4 column: 'ShipAddress_Addr4'
		shipAddressAddr5 column: 'ShipAddress_Addr5'
		shipAddressCity column: 'ShipAddress_City'
		shipAddressState column: 'ShipAddress_State'
		shipAddressPostalCode column: 'ShipAddress_PostalCode'
		shipAddressCountry column: 'ShipAddress_Country'
		shipAddressNote column: 'ShipAddress_Note'
		isPending column: 'IsPending'
		isFinanceCharge column: 'IsFinanceCharge'
		pONumber column: 'PONumber'
		termsRefListID column: 'TermsRef_ListID'
		termsRefFullName column: 'TermsRef_FullName'
		dueDate column: 'DueDate'
		salesRepRefListID column: 'SalesRepRef_ListID'
		salesRepRefFullName column: 'SalesRepRef_FullName'
		fOB column: 'FOB'
		shipDate column: 'ShipDate'
		shipMethodRefListID column: 'ShipMethodRef_ListID'
		shipMethodRefFullName column: 'ShipMethodRef_FullName'
		subtotal column: 'Subtotal'
		itemSalesTaxRefListID column: 'ItemSalesTaxRef_ListID'
		itemSalesTaxRefFullName column: 'ItemSalesTaxRef_FullName'
		salesTaxPercentage column: 'SalesTaxPercentage'
		salesTaxTotal column: 'SalesTaxTotal'
		appliedAmount column: 'AppliedAmount'
		balanceRemaining column: 'BalanceRemaining'
		currencyRefListID column: 'CurrencyRef_ListID'
		currencyRefFullName column: 'CurrencyRef_FullName'
		exchangeRate column: 'ExchangeRate'
		balanceRemainingInHomeCurrency column: 'BalanceRemainingInHomeCurrency'
		memo column: 'Memo'
		isPaid column: 'IsPaid'
		customerMsgRefListID column: 'CustomerMsgRef_ListID'
		customerMsgRefFullName column: 'CustomerMsgRef_FullName'
		isToBePrinted column: 'IsToBePrinted'
		isToBeEmailed column: 'IsToBeEmailed'
		isTaxIncluded column: 'IsTaxIncluded'
		customerSalesTaxCodeRefListID column: 'CustomerSalesTaxCodeRef_ListID'
		customerSalesTaxCodeRefFullName column: 'CustomerSalesTaxCodeRef_FullName'
		suggestedDiscountAmount column: 'SuggestedDiscountAmount'
		suggestedDiscountDate column: 'SuggestedDiscountDate'
		other column: 'Other'
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
		arAccount column: 'ARAccountRef_ListID'
	}

	String id
	
	static transients = ['txnID']
	String txnID
	String timeCreated
	String timeModified
	String editSequence
	Integer txnNumber
	String customerRefListID
	String customerRefFullName
	String classRefListID
	String classRefFullName
	Account arAccount
	String aRAccountRefFullName
	String templateRefListID
	String templateRefFullName
	Date txnDate
	String refNumber
	String billAddressAddr1
	String billAddressAddr2
	String billAddressAddr3
	String billAddressAddr4
	String billAddressAddr5
	String billAddressCity
	String billAddressState
	String billAddressPostalCode
	String billAddressCountry
	String billAddressNote
	String shipAddressAddr1
	String shipAddressAddr2
	String shipAddressAddr3
	String shipAddressAddr4
	String shipAddressAddr5
	String shipAddressCity
	String shipAddressState
	String shipAddressPostalCode
	String shipAddressCountry
	String shipAddressNote
	String isPending
	String isFinanceCharge
	String pONumber
	String termsRefListID
	String termsRefFullName
	Date dueDate
	String salesRepRefListID
	String salesRepRefFullName
	String fOB
	Date shipDate
	String shipMethodRefListID
	String shipMethodRefFullName
	Float subtotal
	String itemSalesTaxRefListID
	String itemSalesTaxRefFullName
	String salesTaxPercentage
	Float salesTaxTotal
	Float appliedAmount
	Float balanceRemaining
	String currencyRefListID
	String currencyRefFullName
	Float exchangeRate
	Float balanceRemainingInHomeCurrency
	String memo
	String isPaid
	String customerMsgRefListID
	String customerMsgRefFullName
	String isToBePrinted
	String isToBeEmailed
	String isTaxIncluded
	String customerSalesTaxCodeRefListID
	String customerSalesTaxCodeRefFullName
	Float suggestedDiscountAmount
	Date suggestedDiscountDate
	String other
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
	}
	
	String toString() {
		return 'Invoice #' + refNumber + ' for ' + customerRefFullName
	}
}
