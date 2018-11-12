package ca.airspeed.wsbooks

import static java.lang.String.format
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Period
import java.time.ZonedDateTime;

class TimesheetService {

	def grailsApplication
	def tsheetsRestService
	def timeTrackingConverterService

	def fetchTimesheetsFromTsheets() {
		assert tsheetsRestService != null
		def nf = NumberFormat.getInstance()
		nf.setMinimumIntegerDigits(1)
		nf.setMinimumFractionDigits(2)
		def df = new SimpleDateFormat('EEE, MMM d')
		def controlRecord = Control.findByRowName("Control Record")
		ZonedDateTime lastFetchedDate = new ZonedDateTime(controlRecord.tsheetsLastFetchedDate).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0)
        ZonedDateTime yesterday = new ZonedDateTime().withHour().withMinute(0).withSecond(0).withNano(0).minusDays(1)
		if (lastFetchedDate.isAfter(yesterday)) {
			log.info(format("TSheets Timesheets will be fetched two days after %s. None were fetched this time.", df.format(lastFetchedDate.toDate())))
			return
		}
		if (Period.between(lastFetchedDate, yesterday).getDays() < 1) {
			log.info("TSheets Timesheets will be fetched tomorrow. None were fetched this time.")
			return
		}
		def json = tsheetsRestService.findTimesheetsByDateBetween(lastFetchedDate.plusDays(1).toDate(), yesterday.toDate())
		def dayTotal = 0.00
		json.results.timesheets.each{ ts ->
			dayTotal += ts.value.duration.toFloat() / 3600f
		}
		log.info(format('TSheets has %s hours between %s and %s.', 
			nf.format(dayTotal), df.format(lastFetchedDate.plusDays(1).toDate()), df.format(yesterday.toDate())))
		def qbTimeTrackingList = timeTrackingConverterService.convertFromTsheetsTimesheets(json)
		qbTimeTrackingList.each {
			it.save(flush: true, failOnError: true)
			log.debug(format("Added to TimeTracking; id: %s, txnDate: %s, entity: %s, duration: %s, Customer: %s, Service Item: %s.",
				it.txnID, it.txnDate, it.entityRefListID, it.duration, it.customer?.fullName, it.itemService?.fullName))
		}
		log.info(format('%s timesheet hours from TSheets written to QuickBooks.', nf.format(dayTotal)))
		controlRecord.tsheetsLastFetchedDate = yesterday.toDate()
		controlRecord.save(failOnError: true, flush: true)
		log.debug(format('Control.tsheetsLastFetchedDate set to %s.', df.format(controlRecord.tsheetsLastFetchedDate)))
		return json
	}
}
