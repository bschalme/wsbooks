import ca.airspeed.wsbooks.http.TsheetsResponseHandler

import com.freshbooks.ApiConnection
// Place your Spring DSL code here
beans = {
	freshbooksApiConn(ApiConnection, '${freshbooks.url}', '${freshbooks.token}', "Airspeed-WsBooks") {
	}
	tsheetsResponseHandler(TsheetsResponseHandler) {
	}
}
