package com.rpwebcrafts.aem.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, enabled = true, service = UserDetails.class)
@Designate(ocd = UserDetailsConfig.class)
public class UserDetails {

    // User Details --->

    private String userName;
    private int userID;
    private String userEmail;
    private String userAPI;
    private int userSecurityCode;

    /* ---> Logger Tool <--- */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetails.class);

    /* ---> Service Methods <--- */
    @Activate
    public void activate(UserDetailsConfig config) {

        updateUserDetails(config);

        LOGGER.info("Data Coming from UserDetails class | UserDetails Activated");
    }

    @Deactivate
    public void deactivate(UserDetailsConfig config) {

        LOGGER.info("Data Coming from UserDetails class | UserDetails Deactivated");
    }

    @Modified
    public void modified(UserDetailsConfig config) {

        LOGGER.info("Data Coming from UserDetails class | UserDetails Modified");
    }

    // User Details method --->
    public void updateUserDetails(UserDetailsConfig config) {

        this.userName = config.getUserName();
        this.userID = config.getUserID();
        this.userEmail = config.getUserEmail();
        this.userAPI = config.getUserAPI();
        this.userSecurityCode = config.getUserSecurityCode();

        LOGGER.info("User Name: {} | User ID: {} | User Email: {} | User API: {} | User Security Code: {}", userName,
                userID, userEmail, userAPI, userSecurityCode);
    }
}
