package ca.airspeed.wsbooks

import java.time.LocalDate
import java.util.Date;

class Recurrence {

    static constraints = {
		nickname blank: false, nullable: false
		entityName(inList: ["Invoice", "General Journal", "Credit Card Charge"], nullable: false)
		entityNumber blank: false, nullable: false
		frequency(inList: ["Daily", "Weekly", "Bi-Weekly", "Monthly", "Quarterly", "Semi-Annually", "Annually"])
		txnTiming(inList: ["In Advance", "In Arrears"])
		previousRunDate editable: false, nullable: true
		nextRunDate(min: new Date(), nullable: true)
		finalRunDate(min: new Date())
		active nullable: false
    }
	
	String nickname = "Web hosting for MegaCorp"
	String entityName = "Invoice"
	String entityNumber
	String frequency = "Monthly"
	String txnTiming = "In Advance"
	Date previousRunDate
	Date nextRunDate = new LocalDate().plusMonths(1).withDayOfMonth(1).toDate()
	Date finalRunDate = new LocalDate().plusMonths(12).withDayOfMonth(1).toDate()
	Boolean active = true
	Date dateCreated
	Date lastUpdated
}
