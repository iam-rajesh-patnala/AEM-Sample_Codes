package com.rpwebcrafts.aem.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "UserDetailsConfiguration", description = "Description for User details configuration")
public @interface UserDetailsConfig {

    // User Name Attribute
    @AttributeDefinition(name = "User Name", description = "Description for user name", defaultValue = "John Doe", required = true, type = AttributeType.STRING)
    public String getUserName();

    // User ID Attribute
    @AttributeDefinition(name = "User ID", description = "Description for user id", defaultValue = "123456", required = true, type = AttributeType.INTEGER)
    public int getUserID();

    // User Email Attribute
    @AttributeDefinition(name = "User Email", description = "Description for user email", defaultValue = "B0M9o@example.com", required = true, type = AttributeType.STRING)
    public String getUserEmail();

    // User API Attribute
    @AttributeDefinition(name = "User API", description = "Description for user API", defaultValue = "https://api.sampleapis.com/codingresources/codingResources", required = true, type = AttributeType.STRING)
    public String getUserAPI();

    // User Security Code Attribute
    @AttributeDefinition(name = "User Security Code", description = "Description for user security code", defaultValue = "123456", required = true, type = AttributeType.INTEGER)
    public int getUserSecurityCode();

}
