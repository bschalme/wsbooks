package ca.airspeed.wsbooks

class ItemService {

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
		unitOfMeasureSetRefListID(nullable: true)
		unitOfMeasureSetRefFullName(nullable: true)
		isTaxIncluded(nullable: false, inList: ['true', 'false'])
		salesTaxCodeRefListID(nullable: true)
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
		status(nullable: false, inList: ['ADD', 'UPDATE', 'DELETE'])
	}

	static mapping = {
		datasource 'opensync'
		table 'itemservice'
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
		unitOfMeasureSetRefListID column: 'UnitOfMeasureSetRef_ListID'
		unitOfMeasureSetRefFullName column: 'UnitOfMeasureSetRef_FullName'
		isTaxIncluded column: 'IsTaxIncluded'
		salesTaxCodeRefListID column: 'SalesTaxCodeRef_ListID'
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
	String unitOfMeasureSetRefListID
	String unitOfMeasureSetRefFullName
	String isTaxIncluded
	String salesTaxCodeRefListID
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
