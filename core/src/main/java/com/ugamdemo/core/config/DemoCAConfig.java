package com.ugamdemo.core.config;


import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "Context aware configuration", description = "Config for CA")

public @interface DemoCAConfig {

    @Property(label = "country name",
            description ="country ref name")
    String siteCountry() default "us";

    @Property(label = "country name",
            description ="country ref name")
    String siteLocale() default "dhawal";
}
