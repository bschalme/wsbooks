package ca.airspeed.wsbooks

class Items {

	static constraints = {
		listID(nullable: false)
		fullName(nullable: false)
		tableName(nullable: false, inList: [
			'itemservice',
			'itemnoninventory',
			'itemothercharge',
			'itemfixedasset',
			'itemsubtotal',
			'itemsalestax',
			'itemsalestaxgroup',
			'itemgroup'
		])
	}

	static mapping = {
		datasource 'opensync'
		table 'items'
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
		return fullName + ' on ' + tableName + '.'
	}
}
