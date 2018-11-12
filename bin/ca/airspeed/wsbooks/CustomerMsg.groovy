package ca.airspeed.wsbooks

class CustomerMsg {

	static constraints = {
		listID(nullable: false)
		timeCreated(nullable: true)
		timeModified(nullable: true)
		editSequence(nullable: true)
		name(nullable: false)
		isActive(nullable: false, inList:['true', 'false'])
		status(nullable: true, inList:[''])
	}

	static mapping = {
		datasource 'opensync'
		table 'customermsg'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		timeCreated column: 'TimeCreated'
		timeModified column: 'TimeModified'
		editSequence column: 'EditSequence'
		name column: 'Name'
		isActive column: 'IsActive'
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
