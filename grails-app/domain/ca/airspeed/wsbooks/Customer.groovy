package ca.airspeed.wsbooks

class Customer {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		fullName(nullable: true)
		isActive(nullable: true)
		parent(nullable: true)
		parentRefFullName(nullable: true)
		sublevel(nullable: true)
		companyName(nullable: true)
		salutation(nullable: true)
		firstName(nullable: true)
		middleName(nullable: true)
		lastName(nullable: true)
		suffix(nullable: true)
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
		printAs(nullable: true)
		phone(nullable: true)
		mobile(nullable: true)
		pager(nullable: true)
		altPhone(nullable: true)
		fax(nullable: true)
		email(nullable: true)
		contact(nullable: true)
		altContact(nullable: true)
		customerTypeRefListID(nullable: true)
		customerTypeRefFullName(nullable: true)
		termsRefListID(nullable: true)
		termsRefFullName(nullable: true)
		salesRepRefListID(nullable: true)
		salesRepRefFullName(nullable: true)
		balance(nullable: true)
		totalBalance(nullable: true)
		salesTaxCodeRefListID(nullable: true)
		salesTaxCodeRefFullName(nullable: true)
		itemSalesTaxRefListID(nullable: true)
		itemSalesTaxRefFullName(nullable: true)
		salesTaxCountry(nullable: true)
		resaleNumber(nullable: true)
		accountNumber(nullable: true)
		creditLimit(nullable: true)
		preferredPaymentMethodRefListID(nullable: true)
		preferredPaymentMethodRefFullName(nullable: true)
		creditCardNumber(nullable: true)
		expirationMonth(nullable: true)
		expirationYear(nullable: true)
		nameOnCard(nullable: true)
		creditCardAddress(nullable: true)
		creditCardPostalCode(nullable: true)
		jobStatus(nullable: true)
		jobStartDate(nullable: true)
		jobProjectedEndDate(nullable: true)
		jobEndDate(nullable: true)
		jobDesc(nullable: true)
		jobTypeRefListID(nullable: true)
		jobTypeRefFullName(nullable: true)
		notes(nullable: true)
		priceLevelRefListID(nullable: true)
		priceLevelRefFullName(nullable: true)
		taxRegistrationNumber(nullable: true)
		currencyRefListID(nullable: true)
		currencyRefFullName(nullable: true)
		isStatementWithParent(nullable: true)
		deliveryMethod(nullable: true)
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
		table 'customer'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		fullName column: 'FullName'
		isActive column: 'IsActive'
		parent column: 'ParentRef_ListID'
		parentRefFullName column: 'ParentRef_FullName'
		sublevel column: 'Sublevel'
		companyName column: 'CompanyName'
		salutation column: 'Salutation'
		firstName column: 'FirstName'
		middleName column: 'MiddleName'
		lastName column: 'LastName'
		suffix column: 'Suffix'
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
		printAs column: 'PrintAs'
		phone column: 'Phone'
		mobile column: 'Mobile'
		pager column: 'Pager'
		altPhone column: 'AltPhone'
		fax column: 'Fax'
		email column: 'Email'
		contact column: 'Contact'
		altContact column: 'AltContact'
		customerTypeRefListID column: 'CustomerTypeRef_ListID'
		customerTypeRefFullName column: 'CustomerTypeRef_FullName'
		termsRefListID column: 'TermsRef_ListID'
		termsRefFullName column: 'TermsRef_FullName'
		salesRepRefListID column: 'SalesRepRef_ListID'
		salesRepRefFullName column: 'SalesRepRef_FullName'
		balance column: 'Balance'
		totalBalance column: 'TotalBalance'
		salesTaxCodeRefListID column: 'SalesTaxCodeRef_ListID'
		salesTaxCodeRefFullName column: 'SalesTaxCodeRef_FullName'
		itemSalesTaxRefListID column: 'ItemSalesTaxRef_ListID'
		itemSalesTaxRefFullName column: 'ItemSalesTaxRef_FullName'
		salesTaxCountry column: 'SalesTaxCountry'
		resaleNumber column: 'ResaleNumber'
		accountNumber column: 'AccountNumber'
		creditLimit column: 'CreditLimit'
		preferredPaymentMethodRefListID column: 'PreferredPaymentMethodRef_ListID'
		preferredPaymentMethodRefFullName column: 'PreferredPaymentMethodRef_FullName'
		creditCardNumber column: 'CreditCardNumber'
		expirationMonth column: 'ExpirationMonth'
		expirationYear column: 'ExpirationYear'
		nameOnCard column: 'NameOnCard'
		creditCardAddress column: 'CreditCardAddress'
		creditCardPostalCode column: 'CreditCardPostalCode'
		jobStatus column: 'JobStatus'
		jobStartDate column: 'JobStartDate'
		jobProjectedEndDate column: 'JobProjectedEndDate'
		jobEndDate column: 'JobEndDate'
		jobDesc column: 'JobDesc'
		jobTypeRefListID column: 'JobTypeRef_ListID'
		jobTypeRefFullName column: 'JobTypeRef_FullName'
		notes column: 'Notes', type: 'text'
		priceLevelRefListID column: 'PriceLevelRef_ListID'
		priceLevelRefFullName column: 'PriceLevelRef_FullName'
		taxRegistrationNumber column: 'TaxRegistrationNumber'
		currencyRefListID column: 'CurrencyRef_ListID'
		currencyRefFullName column: 'CurrencyRef_FullName'
		isStatementWithParent column: 'IsStatementWithParent'
		deliveryMethod column: 'DeliveryMethod'
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
	Customer parent
	String parentRefFullName
	Integer sublevel
	String companyName
	String salutation
	String firstName
	String middleName
	String lastName
	String suffix
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
	String printAs
	String phone
	String mobile
	String pager
	String altPhone
	String fax
	String email
	String contact
	String altContact
	String customerTypeRefListID
	String customerTypeRefFullName
	String termsRefListID
	String termsRefFullName
	String salesRepRefListID
	String salesRepRefFullName
	Float balance
	Float totalBalance
	String salesTaxCodeRefListID
	String salesTaxCodeRefFullName
	String itemSalesTaxRefListID
	String itemSalesTaxRefFullName
	String salesTaxCountry
	String resaleNumber
	String accountNumber
	Float creditLimit
	String preferredPaymentMethodRefListID
	String preferredPaymentMethodRefFullName
	String creditCardNumber
	Integer expirationMonth
	Integer expirationYear
	String nameOnCard
	String creditCardAddress
	String creditCardPostalCode
	String jobStatus
	Date jobStartDate
	Date jobProjectedEndDate
	Date jobEndDate
	String jobDesc
	String jobTypeRefListID
	String jobTypeRefFullName
	String notes
	String priceLevelRefListID
	String priceLevelRefFullName
	String taxRegistrationNumber
	String currencyRefListID
	String currencyRefFullName
	String isStatementWithParent
	String deliveryMethod
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
		return fullName
	}
}
