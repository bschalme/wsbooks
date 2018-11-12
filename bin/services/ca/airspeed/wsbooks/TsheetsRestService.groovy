package ca.airspeed.wsbooks

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import grails.plugins.rest.client.RestBuilder;
import grails.transaction.Transactional
import groovy.json.JsonSlurper;

@Transactional
class TsheetsRestService {

	def grailsApplication
	def tsheetsResponseHandler

	def getLastModifiedTimestamps() {
		def config = grailsApplication?.config
		def token = config.tsheets.rest.token
		RestBuilder rest = new RestBuilder()
		def response = rest.get(config.tsheets.rest.url + 'last_modified_timestamps') {
			header 'Authorization', 'Bearer ' + token
		}
		tsheetsResponseHandler.handle(response)
	}

	def findTimesheetsByDateBetween(Date startDate, Date endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd")
		def config = grailsApplication?.config
		def token = config.tsheets.rest.token
		RestBuilder rest = new RestBuilder()
		def response = rest.get(config.tsheets.rest.url + 'timesheets?start_date=' + df.format(startDate) + '&end_date=' + df.format(endDate)) {
			header 'Authorization', 'Bearer ' + token
		}
		tsheetsResponseHandler.handle(response)
	}
}
