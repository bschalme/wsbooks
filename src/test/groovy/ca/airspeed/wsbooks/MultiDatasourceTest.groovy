package ca.airspeed.wsbooks

import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.support.ConfigurableConversionService

import groovy.transform.CompileStatic

@CompileStatic
trait MultiDatasourceTest {
	def configDatasource(ConfigurableApplicationContext applicationContext, String datasource = "default") {
		System.setProperty("grails.gorm.connections", datasource)

		ConfigurableConversionService conversionService = applicationContext.getEnvironment().getConversionService()
		conversionService.addConverter(new StringToMapConverter())
	}
}

class StringToMapConverter implements Converter<String, Map> {
	@Override
	Map convert(String source) {
		source.split(",").collectEntries({
			[(it):it]
		})
	}
}
