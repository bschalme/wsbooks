package ca.airspeed.wsbooks

import static java.lang.String.format
import static org.joda.time.Days.daysBetween
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.Days;

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
		DateTime lastFetchedDate = new DateTime(controlRecord.tsheetsLastFetchedDate)
		DateTime yesterday = new DateTime().minusDays(1)
		if (lastFetchedDate.isAfter(yesterday)) {
			log.info(format("TSheets Timesheets will be fetched two days after %s. None were fetched this time.", df.format(lastFetchedDate.toDate())))
			return
		}
		if (daysBetween(lastFetchedDate, yesterday) < Days.ONE) {
			log.info("TSheets Timesheets will be fetched tomorrow. None were fetched this time.")
			return
		}
		def jsonSlurper = new JsonSlurper()
		def tsheetsTimesheets = tsheetsRestService.findTimesheetsByDateBetween(lastFetchedDate.plusDays(1).toDate(), yesterday.toDate())
		def json = jsonSlurper.parseText(tsheetsTimesheets)
		def dayTotal = 0.00
		json.results.timesheets.each{ ts ->
			println ts
			dayTotal += ts.value.duration.toFloat() / 3600f
		}
		log.info(format('TSheets has %s hours between %s and %s. Adding them to QuickBooks.', 
			nf.format(dayTotal), df.format(lastFetchedDate.plusDays(1).toDate()), df.format(yesterday.toDate())))
		def qbTimeTrackingList = timeTrackingConverterService.convertFromTsheetsTimesheets(json)
		qbTimeTrackingList.each {
			it.save(flush: true, failOnError: true)
			log.info(format("Added to TimeTracking; id: %s, txnDate: %s, entity: %s, duration: %s, Customer: %s, Service Item: %s",
				it.txnID, it.txnDate, it.entityRefListID, it.duration, it.customer?.fullName, it.itemService?.fullName))
		}
		controlRecord.tsheetsLastFetchedDate = yesterday.toDate()
		controlRecord.save(failOnError: true, flush: true)
	}
}
