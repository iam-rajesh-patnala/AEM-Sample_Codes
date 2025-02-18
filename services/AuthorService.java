package com.rpwebcrafts.aem.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class AuthorService {

    /* ---> Logger Tool <--- */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);

    // Referencing the service
    @Reference
    PublishService publishService;

    // Activating the service
    @Activate
    public void activate() {
        LOGGER.info("AuthorService is activated");

        LOGGER.info("Result from PublishService: {}", publishService.greetings());
    }

    // Deactivating the service
    @Deactivate
    public void deactivate() {
        LOGGER.info("AuthorService is deactivated");
    }

    // Modifying the service
    @Modified
    public void modified() {
        LOGGER.info("AuthorService is modified");
    }

}