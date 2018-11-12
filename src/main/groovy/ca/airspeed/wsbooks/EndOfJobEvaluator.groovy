/**
 * 
 */
package ca.airspeed.wsbooks

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase
import org.apache.log4j.spi.LoggingEvent
import org.apache.log4j.spi.TriggeringEventEvaluator;

/**
 * @author Brian Schalme
 *
 */
class EndOfJobEvaluator implements TriggeringEventEvaluator {

	/**
	 * @see org.apache.log4j.spi.TriggeringEventEvaluator#isTriggeringEvent(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public boolean isTriggeringEvent(LoggingEvent event) {
		return containsIgnoreCase(event.getRenderedMessage(), "End of job");
	}

}
