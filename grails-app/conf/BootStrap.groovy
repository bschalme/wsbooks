class BootStrap {
	
	def grailsApplication

    def init = { servletContext ->
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

	def destroy = {
    }
}
