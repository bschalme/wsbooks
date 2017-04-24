quartzJobs {
	jobs = [
		'FetchTimesheetsJob': [
			cronTriggers: [
				cronExpression: '0 15 6 * * ?'
			]
		],
		'WorkInProcessJob': [
			cronTriggers: [
				cronExpression: '0 20 6 * * ?'
			]
		],
		'MonthlyInvoiceJob': [
			cronTriggers: [
				cronExpression: '0 25 6 1 * ?'
			]
		],
		'EndOfScheduleJob': [
			cronTriggers: [
				cronExpression: '0 30 6 * * ?'
			]
		]
	]
}
