import grails.rest.render.hal.HalJsonRenderer;
import ca.airspeed.wsbooks.TimeTracking;
import ca.airspeed.wsbooks.http.TsheetsResponseHandler

import com.freshbooks.ApiConnection
// Place your Spring DSL code here
beans = {
	freshbooksApiConn(ApiConnection, '${freshbooks.url}', '${freshbooks.token}', "Airspeed-WsBooks") {
	}
	tsheetsResponseHandler(TsheetsResponseHandler) {
	}

	halTimeTrackingRenderer(HalJsonRenderer, TimeTracking)
}
