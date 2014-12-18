package ca.airspeed.wsbooks

class Control {
	String rowName
	Date tsheetsLastFetchedDate

	static constraints = {
		rowName(editable: false)
		tsheetsLastFetchedDate(format: "EEE MMM dd, yyyy")
	}

	String toString() {
		return rowName
	}
}
