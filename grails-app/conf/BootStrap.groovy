import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import ca.airspeed.wsbooks.Control;

class BootStrap {
	
	def grailsApplication

    def init = { servletContext ->
		seedDatabase()
		initializeJobs()
    }

	def destroy = {
    }

	private void seedDatabase() {
		def controlData = Control.findAll()
		if (!controlData) {
			Control seedControl = new Control()
			seedControl.rowName = "Control Record"
			seedControl.tsheetsLastFetchedDate = new DateMidnight().minusDays(1).toDate()
			seedControl.save(failOnError: true, flush: true)
		}
	}

	private void initializeJobs() {
		def jobs = grailsApplication.config.quartzJobs.jobs
    	if (jobs) {
            jobs.each { job, details ->
                List triggers = (details?.cronTriggers instanceof Map) ? [details.cronTriggers]: details.cronTriggers
                if (triggers) {
                    def j = Class.forName(job)
                    triggers.each { trigger ->
                        String cronExpression = trigger.cronExpression ?: '1 1 1 1 1 ? 2099'
						log.info("Scheduling " + job + " for " + cronExpression)
                        j.schedule(cronExpression)
                    }
                }
            }
        }
	}
}
