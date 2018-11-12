package ca.airspeed.wsbooks

import org.springframework.security.access.annotation.Secured;

import grails.artefact.Artefact;
import grails.gorm.transactions.Transactional
import grails.rest.RestfulController;

@Secured('ROLE_USER')
@Artefact("Controller")
@Transactional
class TimeTrackingController extends RestfulController<TimeTracking> {
	static responseFormats = ['json', 'xml']

    TimeTrackingController() {
		super(TimeTracking)
	}
}
