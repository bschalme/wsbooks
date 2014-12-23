package ca.airspeed.wsbooks

class TsheetsUserXref {
	String userName
	Integer tsheetsUserId
	String qbEntityListId

    static constraints = {
		userName(nullable: false, blank: false)
    }
	
	String toString() {
		return userName
	}
}
