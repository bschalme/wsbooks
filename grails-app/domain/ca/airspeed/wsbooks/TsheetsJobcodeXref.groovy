package ca.airspeed.wsbooks

class TsheetsJobcodeXref {
	String jobName
	Integer tsheetsJobcodeId
	String qbCustomerListId
	String qbItemServiceListId

    static constraints = {
        jobName(nullable: false, blank: false)
    }
	
	String toString() {
		return jobName
	}
}
