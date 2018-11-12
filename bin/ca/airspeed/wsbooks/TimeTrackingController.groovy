package ca.airspeed.wsbooks

import org.springframework.security.access.annotation.Secured;

import grails.artefact.Artefact;
import grails.rest.RestfulController;
import grails.transaction.Transactional;

@Secured('ROLE_USER')
@Artefact("Controller")
@Transactional
class TimeTrackingController extends RestfulController<TimeTracking> {
	static responseFormats = ['json', 'xml']

    TimeTrackingController() {
		super(TimeTracking)
	}
}
