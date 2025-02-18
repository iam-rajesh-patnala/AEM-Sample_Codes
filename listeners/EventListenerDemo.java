package com.rpwebcrafts.aem.core.listeners;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

/**
 * An EventListener implementation that listens for JCR events in AEM.
 */
@Component(immediate = true, service = EventListener.class)
public class EventListenerDemo implements EventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerDemo.class);

	private Session session;

	// Injecting SlingRepository to interact with JCR repository
	@Reference
	private SlingRepository slingRepository;

	/**
	 * Activates the event listener when the component is activated. This method
	 * sets up the event listener for specific node types and paths.
	 */
	@Activate
	public void activate() {
		try {
			// Define node types to listen for
			String[] nodetypes = { "cq:PageContent" };

			// Login to the repository using a system user (Demouser must be a system user
			// configured in AEM)
			session = slingRepository.loginService("DemoUser", null);

			// Register the event listener with the JCR ObservationManager
			session.getWorkspace().getObservationManager().addEventListener(this, // Event handler (this class)
					Event.NODE_ADDED | Event.PROPERTY_ADDED, // Event types to listen for
					"/content/rpwebcrafts/us/en", // JCR path to listen on
					true, // Listen for events in child nodes (deep listening)
					null, // No specific UUIDs filter
					nodetypes, // Filter by specific node types
					true // Listen for local events only
			);

			LOGGER.info("Event Listener registered successfully.");

		} catch (RepositoryException error) {
			LOGGER.error("Error while adding Event Listener: {}", error.getMessage(), error);
		}
	}

	/**
	 * Called when an event occurs in the repository. Logs the path of the added
	 * node or property.
	 */
	@Override
	public void onEvent(EventIterator eventIterator) {
		try {
			while (eventIterator.hasNext()) {
				Event event = eventIterator.nextEvent(); // Retrieve the next event
				LOGGER.info("Path of the event: {}", event.getPath()); // Log the event path
			}
		} catch (RepositoryException e) {
			LOGGER.error("Error while processing events: {}", e.getMessage(), e);
		}
	}

	/**
	 * Deactivate method to clean up resources.
	 */
	@Deactivate
	protected void deactivate() {
		if (session != null) {
			session.logout(); // Logout from the JCR session when the component is deactivated
			LOGGER.info("Session logged out successfully.");
		}
	}
}
