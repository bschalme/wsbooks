package ca.airspeed.wsbooks

import groovy.util.logging.Slf4j;

import org.joda.time.LocalDate;

class RecurrenceService {

	def increment(Recurrence r) {
		def baseDate = new LocalDate(r.nextRunDate)
		def nextRunDate
		r.nextRunDate = null
		switch (r.frequency) {
			case 'Monthly':
				nextRunDate = baseDate.plusMonths(1).withDayOfMonth(1).toDate()
				break
		}
		if (nextRunDate <= r.finalRunDate) {
			r.nextRunDate = nextRunDate
		}
		r.save(failOnError:true, flush: true)
	}
}
