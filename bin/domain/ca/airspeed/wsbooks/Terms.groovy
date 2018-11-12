package ca.airspeed.wsbooks

class Terms {

	static constraints = {
		listID(nullable: false)
		fullName(nullable: false)
		tableName(nullable: true)
	}

	static mapping = {
		datasource 'opensync'
		table 'terms'
		version false
		id column: 'ListID', generator: 'assigned'
		listID column: 'ListID'
		fullName column: 'FullName'
		tableName column: 'TableName'
	}

	String id

	static transients = ['listID']
	String listID
	String fullName
	String tableName

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
