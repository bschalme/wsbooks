package ca.airspeed.wsbooks.http

import static org.springframework.http.HttpStatus.OK

class TsheetsResponseHandler {

	def handle(Object response) {
		if (response.statusCode == OK) {
			return response.body.toString()
		}
		return ""
	}
}
