package ca.airspeed.wsbooks.http

import static java.lang.String.format
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static org.apache.commons.lang.StringUtils.EMPTY
import groovy.json.JsonSlurper;
import org.springframework.http.HttpStatus
import ca.airspeed.wsbooks.exception.TsheetsException

class TsheetsResponseHandler {

	def handle(Object theResponse) {
		def statusCode = theResponse?.statusCode
		switch (statusCode) {
			case OK:
			    return theResponse.json
            default:
			    def messageStr = makeMessage(statusCode, theResponse.text)
			    log.error messageStr
				throw new TsheetsException(messageStr)
		}
	}
	
	private String makeMessage(HttpStatus statusCode, String bodyString) {
		def jsonSlurper = new JsonSlurper()
		def json = jsonSlurper.parseText(bodyString)
        return format("TSheets returned a %d %s. %s.", statusCode.value(), statusCode.reasonPhrase, json.error_description)
	}
}
