package com.rpwebcrafts.aem.core.listeners;

import org.osgi.service.event.EventHandler;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = EventHandler.class, property = {

		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
		EventConstants.EVENT_TOPIC + "=com/day/cq/application",

		EventConstants.EVENT_FILTER + "=(|(|(path=/apps/rpwebcrafts/*)(path=/content/we-retail/*))(type=activate))" })
public class EventHandlerDemo implements EventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventHandlerDemo.class);

	@Override
	public void handleEvent(final Event event) {

		LOGGER.debug("Event Handler Demo is now running......");

		LOGGER.info("Event topic: {}", event.getTopic());

		String[] names = event.getPropertyNames();

		for (String name : names) {
			LOGGER.info("Property name: {}, Property value: {}", name, event.getProperty(name));
		}
	}
}
