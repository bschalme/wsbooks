import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import ca.airspeed.wsbooks.Control;
import ca.airspeed.wsbooks.Role;
import ca.airspeed.wsbooks.User;
import ca.airspeed.wsbooks.UserRole;

class BootStrap {
	
	def grailsApplication

    def init = { servletContext ->
		seedControlData()
		seedSecurity()
		initializeJobs()
    }

	def destroy = {
    }

	private void seedControlData() {
		def controlData = Control.findAll()
		if (!controlData) {
			Control seedControl = new Control()
			seedControl.rowName = "Control Record"
			seedControl.tsheetsLastFetchedDate = new DateMidnight().minusDays(1).toDate()
			seedControl.save(failOnError: true, flush: true)
		}
	}

	private void seedSecurity() {
		def adminRole = new Role('ROLE_ADMIN').save()
		def userRole = new Role('ROLE_USER').save()
  
		def testUser = new User('admin', 'admin')
		testUser.passwordExpired = false
		testUser.save()
  
		UserRole.create testUser, adminRole, true
		UserRole.create testUser, userRole, true
  
		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 2
  
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
