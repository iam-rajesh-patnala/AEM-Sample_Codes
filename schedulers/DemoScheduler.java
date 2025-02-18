package com.rpwebcrafts.aem.core.schedulers;

// Importing necessary libraries from Apache Sling, OSGi, and SLF4J
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Defining the component as an OSGi service that implements Runnable interface, and it is immediately activated upon deployment
@Component(service = Runnable.class, immediate = true)
// Designating the configuration interface for this component
@Designate(ocd = DemoSchedulerConfig.class)
public class DemoScheduler implements Runnable {

	// Logger instance to log messages for debugging and information
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoScheduler.class);

	// Reference to the Scheduler service provided by Apache Sling
	@Reference
	private Scheduler scheduler;

	// Variable to hold the name of the scheduler
	private String schedulerName;

	// Overriding the run() method from Runnable interface
	@Override
	public void run() {
		LOGGER.debug("DemoScheduler is now running");
		// Implement the logic here instead of throwing an exception
		// Example logic for the scheduled task
		LOGGER.info("Executing scheduled task in DemoScheduler");
	}

	// Method called during activation of the OSGi component
	@Activate
	protected void activate(final DemoSchedulerConfig config) {
		LOGGER.debug("DemoScheduler is now activated");

		// Retrieving the scheduler name from the configuration
		schedulerName = config.getSchedulerName();

		// Creating ScheduleOptions with the provided cron expression from the
		// configuration
		ScheduleOptions options = scheduler.EXPR(config.getSchedulerCronExpression());
		options.name(schedulerName); // Setting the scheduler name
		options.canRunConcurrently(false); // Preventing concurrent execution of the scheduler

		// Activating the scheduler with the specified options
		activatingScheduler(options);
	}

	// Method called during deactivation of the OSGi component
	@Deactivate
	protected void deactivate() {
		LOGGER.debug("DemoScheduler is now deactivated");
		// Deactivating the scheduler
		deactivatingScheduler();
	}

	// Method to activate and schedule the task with provided ScheduleOptions
	public void activatingScheduler(ScheduleOptions options) {
		if (scheduler != null) { // Checking if the scheduler reference is not null
			scheduler.schedule(this, options); // Scheduling the current Runnable instance
			LOGGER.info("Scheduler activated with cron expression: {}", options);
		} else {
			LOGGER.error("Scheduler reference is null");
		}
	}

	// Method to unschedule or deactivate the running scheduler
	public void deactivatingScheduler() {
		if (scheduler != null) { // Checking if the scheduler reference is not null
			scheduler.unschedule(schedulerName); // Unscheduling the task using its name
			LOGGER.info("Scheduler with name '{}' has been deactivated", schedulerName);
		} else {
			LOGGER.error("Scheduler reference is null");
		}
	}
}
