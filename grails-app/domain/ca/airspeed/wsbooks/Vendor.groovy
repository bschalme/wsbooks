package ca.airspeed.wsbooks

import grails.rest.Resource

@Resource(uri='/Vendor', formats=['json', 'xml'])
class Vendor {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		isActive(nullable: false, inList: ['true', 'false'])
		companyName(nullable: true)
		salutation(nullable: true)
		firstName(nullable: true)
		middleName(nullable: true)
		lastName(nullable: true)
		suffix(nullable: true)
		vendorAddressAddr1(nullable: true)
		vendorAddressAddr2(nullable: true)
		vendorAddressAddr3(nullable: true)
		vendorAddressAddr4(nullable: true)
		vendorAddressAddr5(nullable: true)
		vendorAddressCity(nullable: true)
		vendorAddressState(nullable: true)
		vendorAddressPostalCode(nullable: true)
		vendorAddressCountry(nullable: true)
		vendorAddressNote(nullable: true)
		phone(nullable: true)
		mobile(nullable: true)
		pager(nullable: true)
		altPhone(nullable: true)
		fax(nullable: true)
		email(nullable: true)
		contact(nullable: true)
		altContact(nullable: true)
		nameOnCheck(nullable: true)
		notes(nullable: true)
		accountNumber(nullable: true)
		vendorTypeRefListID(nullable: true)
		vendorTypeRefFullName(nullable: true)
		terms(nullable: true)
		termsRefFullName(nullable: true)
		creditLimit(nullable: true)
		vendorTaxIdent(nullable: true)
		isVendorEligibleFor1099(nullable: true)
		balance(nullable: true)
		isSalesTaxAgency(nullable: true)
		currencyRefListID(nullable: true)
		currencyRefFullName(nullable: true)
		billingRateRefListID(nullable: true)
		billingRateRefFullName(nullable: true)
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
		table 'Vendor'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		isActive column: 'IsActive'
		companyName column: 'CompanyName'
		salutation column: 'Salutation'
		firstName column: 'FirstName'
		middleName column: 'MiddleName'
		lastName column: 'LastName'
		suffix column: 'Suffix'
		vendorAddressAddr1 column: 'VendorAddress_Addr1'
		vendorAddressAddr2 column: 'VendorAddress_Addr2'
		vendorAddressAddr3 column: 'VendorAddress_Addr3'
		vendorAddressAddr4 column: 'VendorAddress_Addr4'
		vendorAddressAddr5 column: 'VendorAddress_Addr5'
		vendorAddressCity column: 'VendorAddress_City'
		vendorAddressState column: 'VendorAddress_State'
		vendorAddressPostalCode column: 'VendorAddress_PostalCode'
		vendorAddressCountry column: 'VendorAddress_Country'
		vendorAddressNote column: 'VendorAddress_Note'
		phone column: 'Phone'
		mobile column: 'Mobile'
		pager column: 'Pager'
		altPhone column: 'AltPhone'
		fax column: 'Fax'
		email column: 'Email'
		contact column: 'Contact'
		altContact column: 'AltContact'
		nameOnCheck column: 'NameOnCheck'
		notes column: 'Notes', type: 'text'
		accountNumber column: 'AccountNumber'
		vendorTypeRefListID column: 'VendorTypeRef_ListID'
		vendorTypeRefFullName column: 'VendorTypeRef_FullName'
		terms column: 'TermsRef_ListID'
		termsRefFullName column: 'TermsRef_FullName'
		creditLimit column: 'CreditLimit'
		vendorTaxIdent column: 'VendorTaxIdent'
		isVendorEligibleFor1099 column: 'IsVendorEligibleFor1099'
		balance column: 'Balance'
		isSalesTaxAgency column: 'IsSalesTaxAgency'
		currencyRefListID column: 'CurrencyRef_ListID'
		currencyRefFullName column: 'CurrencyRef_FullName'
		billingRateRefListID column: 'BillingRateRef_ListID'
		billingRateRefFullName column: 'BillingRateRef_FullName'
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
	String companyName
	String salutation
	String firstName
	String middleName
	String lastName
	String suffix
	String vendorAddressAddr1
	String vendorAddressAddr2
	String vendorAddressAddr3
	String vendorAddressAddr4
	String vendorAddressAddr5
	String vendorAddressCity
	String vendorAddressState
	String vendorAddressPostalCode
	String vendorAddressCountry
	String vendorAddressNote
	String phone
	String mobile
	String pager
	String altPhone
	String fax
	String email
	String contact
	String altContact
	String nameOnCheck
	String notes
	String accountNumber
	String vendorTypeRefListID
	String vendorTypeRefFullName
	Terms terms
	String termsRefFullName
	Float creditLimit
	String vendorTaxIdent
	String isVendorEligibleFor1099
	Float balance
	String isSalesTaxAgency
	String currencyRefListID
	String currencyRefFullName
	String billingRateRefListID
	String billingRateRefFullName
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
		return name
	}
}
