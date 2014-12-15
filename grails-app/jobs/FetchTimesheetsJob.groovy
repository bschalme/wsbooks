class FetchTimesheetsJob {
    static triggers = {}

	def timesheetService

	def description = "Fetch Timesheets"	

	def execute() {
        timesheetService.fetchTimesheetsFromTsheets()
    }
}
