package ca.airspeed.wsbooks

import grails.plugins.rest.client.RestBuilder;
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;

class TsheetsService {
	def grailsApplication
	
	def getToken() {
		assert grailsApplication != null
		def config = grailsApplication.config
		def rest = new RestBuilder()
		def response = rest.get(config.tsheets.url + '?action={action}&api_key={api_key}&username={username}&password={password}&output_format={output_format}') {
			urlVariables( [ action:'get_token', 
				api_key:config.tsheets.apiKey, 
				username:config.tsheets.username, 
				password:config.tsheets.password, 
				output_format:'json' ] )
		}
		if (response.statusCode != HttpStatus.OK) {
			log.error('TSheets get_token returned an HTTP ' + response.statusCode + ' from TSheets.')
			return
		}
		if (!response.hasBody()) {
			log.error('Empty body returned from TSheets get_token.')
			return
		}
		def body = new JsonSlurper().parseText(response.body.toString())
		if (body.status != 'ok') {
			log.error('TSheets get_token returned status: ' + body.status + ', error_code: ' + body.error_code + ', error_text: ' + body.error_text)
			return
		}
		return body.token
	}
	
	def checkStatus() {
		def token = getToken()
		log.info('Logged into TSheets.')
		logout(token)
		log.info('Logged out of TSheets')
	}
	
	def logout(String token) {
		assert grailsApplication != null
		log.debug 'Using token: ' + token
		def config = grailsApplication.config
		def rest = new RestBuilder()
		def response = rest.get(config.tsheets.url + '?action={action}&token={token}&output_format={output_format}') {
			urlVariables( [ action:'logout',
				token:token,
				output_format:'json' ] )
		}
		if (response.statusCode != HttpStatus.OK) {
			log.warn('TSheets logout returned an HTTP ' + response.statusCode + ' from TSheets.')
			return
		}
		if (!response.hasBody()) {
			log.warn('Empty body returned from TSheets logout.')
			return
		}
		def body = new JsonSlurper().parseText(response.body.toString())
		if (body.status != 'ok') {
			def txt = 'TSheets logout returned status: ' + body.status + ', error_code: ' + body.error_code + ', error_text: ' + body.error_text
			log.warn(txt)
			return
		}

	}

	def findTimesheetsByDateBetween(Date startDate, Date endDate) {
		assert grailsApplication != null
		def config = grailsApplication.config
		def token = getToken()
		def rest = new RestBuilder()
		def df = new SimpleDateFormat('yyyy-MM-dd')
		def response = rest.get(config.tsheets.url + '?action={action}&token={token}&start_date={startDate}&end_date={endDate}&output_format={output_format}') {
			urlVariables( [ action:'get_timesheets',
				token:token,
				startDate:df.format(startDate),
				endDate:df.format(endDate),
				output_format:'json' ] )
		}
		if (response.statusCode != HttpStatus.OK) {
			log.warn('TSheets findTimesheetsByDateBetween returned an HTTP ' + response.statusCode + ' from TSheets.')
			return []
		}
		if (!response.hasBody()) {
			log.warn('Empty body returned from TSheets findTimesheetsByDateBetween.')
			return []
		}
		def body = new JsonSlurper().parseText(response.body.toString())
		if (body.status != 'ok') {
			log.warn('TSheets findTimesheetsByDateBetween returned status: ' + body.status + ', last_error: ' + body.last_error + ', error_code: ' + body.error_code + ', error_text: ' + body.error_text)
			return []
		}
		println JsonOutput.prettyPrint(response.body.toString())
		logout(token)
		return body.data
	}	
}
