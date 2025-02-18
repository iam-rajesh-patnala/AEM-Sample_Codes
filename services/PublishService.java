package com.rpwebcrafts.aem.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = PublishService.class)
public class PublishService {

    /* ---> Logger Tool <--- */
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishService.class);

    // Activating the service
    @Activate
    public void activate() {
        LOGGER.info("PublishService Activated");
    }

    // Deactivating the service
    @Deactivate
    public void deactivate() {
        LOGGER.info("PublishService Deactivated");
    }

    // Modifying the service
    @Modified
    public void modified() {
        LOGGER.info("PublishService Modified");
    }

    // Custom method
    public String greetings() {
        return "Hello AEM Developers from PublishService";
    }

}
