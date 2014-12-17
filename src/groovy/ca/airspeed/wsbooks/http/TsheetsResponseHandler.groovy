package ca.airspeed.wsbooks.http

import static org.springframework.http.HttpStatus.OK

class TsheetsResponseHandler {

	def handle(Object theResponse) {
		if (theResponse?.statusCode == OK) {
			return theResponse.body.toString()
		}
		return ""
	}
}
