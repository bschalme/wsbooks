package ca.airspeed.wsbooks

import grails.util.Holders
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

class SampleService {
	def tsheetsService

	def doSomeWork() {
		def nf = NumberFormat.getInstance()
		nf.setMinimumIntegerDigits(1)
		nf.setMinimumFractionDigits(2)
		def activeCusts = Customer.findAllByIsActive("true")
		def haveBillableTimeEntries = false
		activeCusts.each { cust ->
			def timeEntries = TimeTracking.findAllByCustomerAndBillableStatus(cust, 'Billable')
			def i = timeEntries.size()
			if (i > 0) {
				haveBillableTimeEntries = true
				def mm = 0
				timeEntries.each { e ->
					mm += e.durationInMinutes
				}
				log.info('QuickBooks ' + cust.fullName + ' has ' + i + ' billable TimeTracking entries for ' + nf.format(mm / 60) + ' hours.')
			}
		}
		if (!haveBillableTimeEntries) {
			log.info('QuickBooks has no billable TimeTracking entries.')
		}
	}

	def logEndOfJob() {
		log.info('End of job.')
	}

}
