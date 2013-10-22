package ca.airspeed.wsbooks

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

class TimesheetService {
	def tsheetsService

    def fetchTimesheetsFromTsheets() {
		assert tsheetsService != null
		def nf = NumberFormat.getInstance()
		nf.setMinimumIntegerDigits(1)
		nf.setMinimumFractionDigits(2)
		DateTime yesterday = new DateTime().minusDays(1)
		def tsheetsTimesheets = tsheetsService.findTimesheetsByDateBetween(yesterday.toDate(), yesterday.toDate())
		def dayTotal = 0.00
		def df = new SimpleDateFormat('EEE, MMM d')
		tsheetsTimesheets.each{ ts ->
			dayTotal += ts.total_hours.toFloat()
		}
		log.info('TSheets has ' + nf.format(dayTotal) + ' hours for ' + df.format(yesterday.toDate()) + '.')
    }
}
