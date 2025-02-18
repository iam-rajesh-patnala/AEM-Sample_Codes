package com.rpwebcrafts.aem.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class LoggerTool {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTool.class);

	@Activate
	public void getUserInfo() {

		LOGGER.info("Fetching user information...");

		try {
			// Some code to fetch user details
			LOGGER.trace("Trace message ---------->");
			LOGGER.debug("Debug message ---------->");
			LOGGER.info("Info: User data found: John Doe");
			LOGGER.warn("Warning message ---------->");
			LOGGER.error("Error message ---------->");
		} catch (Exception e) {
			LOGGER.error("Error fetching user data", e);
		}
	}
}
