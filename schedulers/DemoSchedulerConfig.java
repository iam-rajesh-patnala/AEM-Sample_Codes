package com.rpwebcrafts.aem.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

/**
 * OSGi Configuration interface for the Demo Scheduler. This class defines
 * various configuration parameters for the scheduler.
 */
@ObjectClassDefinition(name = "Demo Scheduler Configuration", description = "Configuration for Demo Scheduler")
public @interface DemoSchedulerConfig {

	/**
	 * Defines the name of the scheduler.
	 *
	 * @return The scheduler name as a String.
	 */
	@AttributeDefinition(name = "Scheduler Name", 
						 type = AttributeType.STRING, 
						 description = "Enter Scheduler Name"
						 )
	String getSchedulerName();

	/**
	 * Defines the cron expression for the scheduler. Default value is set to run
	 * every 30 seconds.
	 *
	 * @return The cron expression as a String.
	 */
	@AttributeDefinition(name = "Scheduler Cron Expression", 
						 type = AttributeType.STRING, 
						 description = "Enter Scheduler Cron Expression (Quartz format)"
						 )
	String getSchedulerCronExpression() default "0/30 * * * * ?";

	/**
	 * Defines the available gender options for the scheduler. Default values
	 * include "Male", "Female", and "Other".
	 *
	 * @return An array of gender options.
	 */
	@AttributeDefinition(name = "Gender", 
						 type = AttributeType.STRING, 
						 description = "Select Gender"
						 )
	String[] getGender() default { "Male", "Female", "Other" };

	/**
	 * Defines the status of the scheduler. Default value is set to false
	 * (inactive).
	 *
	 * @return The status as a boolean.
	 */
	@AttributeDefinition(name = "Status", 
						 type = AttributeType.BOOLEAN, 
						 description = "Enter Status"
						 )
	boolean getStatus() default false;

	/**
	 * Defines the country options for the scheduler. Default value is set to
	 * "India". Available options include India, USA, UK, and Australia.
	 *
	 * @return The selected country as a String.
	 */
	@AttributeDefinition(name = "Country", 
						 type = AttributeType.STRING, 
						 options = {
										@Option(value = "India", label = "India"), @Option(value = "USA", label = "USA"),
										@Option(value = "UK", label = "UK"),
										@Option(value = "Australia", label = "Australia") 
						 		   }, 
						description = "Select Country"
						)
	String getCountry() default "India";
}
