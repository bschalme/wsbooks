package ca.airspeed.wsbooks.converter

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import ca.airspeed.wsbooks.Customer;
import ca.airspeed.wsbooks.ItemService;
import ca.airspeed.wsbooks.TimeTracking;
import ca.airspeed.wsbooks.TsheetsJobcodeXref;
import ca.airspeed.wsbooks.TsheetsUserXref;
import grails.transaction.Transactional

@Transactional
class TimeTrackingConverterService {

    def convertFromTsheetsTimesheets(Map<String, Object> tsheets) {
        def results = []
		tsheets.results.timesheets.each {
			TimeTracking qbTime = new TimeTracking()
			qbTime.txnID = Integer.valueOf(it.value.id)
			qbTime.txnDate = new LocalDate(it.value.date).toDate()
			qbTime.entityRefListID = TsheetsUserXref.findByTsheetsUserId(it.value.user_id)?.qbEntityListId
			Integer minutes = Integer.valueOf(it.value.duration) / 60
			qbTime.duration = 'PT' + ((Integer)minutes / 60) + 'H' + minutes % 60 + 'M'
			def jobcodeXref = TsheetsJobcodeXref.findByTsheetsJobcodeId(it.value.jobcode_id)
			qbTime.customer = Customer.get(jobcodeXref?.qbCustomerListId)
			qbTime.itemService = ItemService.get(jobcodeXref?.qbItemServiceListId)
			def startTime = new DateTime(it.value.start).toDate()
			def endTime = new DateTime(it.value.end).toDate()
			def df = new SimpleDateFormat("HHmm")
			qbTime.notes = df.format(startTime) + '-' + df.format(endTime) + ':' + it.value.notes
			qbTime.billableStatus = 'Billable'
			qbTime.status = 'ADD'
            results << qbTime
		}
		return results
    }
}
