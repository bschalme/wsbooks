package ca.airspeed.wsbooks

import grails.plugins.rest.client.RestBuilder;
import grails.transaction.Transactional

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
	
}
