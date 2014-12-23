package ca.airspeed.wsbooks

class TsheetsJobcodeXref {
	String jobName
	Integer tsheetsJobcodeId
	String qbCustomerListId
	String qbItemServiceListId

    static constraints = {
    }
	
	String toString() {
		return jobName
	}
}
