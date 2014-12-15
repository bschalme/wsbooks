class WorkInProcessJob {
    static triggers = {}

	def sampleService

	def description = "Report on Work in Process"

	def execute() {
        sampleService.doSomeWork()
    }
}
