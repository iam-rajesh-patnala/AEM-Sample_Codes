package com.rpwebcrafts.aem.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Student.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StudentImplementation implements Student {

    @ValueMapValue
    public String firstname;

    @ValueMapValue
    public String lastname;

    @ValueMapValue
    public String currentaddress;

    @ValueMapValue
    public String permanentaddress;

    @ValueMapValue
    public String country;

    @ValueMapValue
    public String checkbox;

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public String getLastName() {
        return lastname;
    }

    @Override
    public String getCurrentAddress() {
        return currentaddress;
    }

    @Override
    public String getPermanentAddress() {
        return permanentaddress;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getStatus() {
        return checkbox;
    }

}
