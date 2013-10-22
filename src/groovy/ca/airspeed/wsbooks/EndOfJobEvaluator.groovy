/**
 * 
 */
package ca.airspeed.wsbooks

import org.apache.log4j.spi.LoggingEvent
import org.apache.log4j.spi.TriggeringEventEvaluator;

/**
 * @author Brian Schalme
 *
 */
class EndOfJobEvaluator implements TriggeringEventEvaluator {

	/* (non-Javadoc)
	 * @see org.apache.log4j.spi.TriggeringEventEvaluator#isTriggeringEvent(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public boolean isTriggeringEvent(LoggingEvent event) {
		return event.getRenderedMessage().contains("End of job");
	}

}
