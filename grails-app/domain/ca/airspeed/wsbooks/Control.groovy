package ca.airspeed.wsbooks

import grails.persistence.Entity

@Entity
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
