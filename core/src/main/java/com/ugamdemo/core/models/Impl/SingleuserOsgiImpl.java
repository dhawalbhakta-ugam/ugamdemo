package com.ugamdemo.core.models.Impl;

import com.ugamdemo.core.models.SingleuserOsgi;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@Component(service = SingleuserOsgi.class,immediate = true)
@Designate(ocd = SingleuserOsgiImpl.SingleConfig.class )
public class SingleuserOsgiImpl implements SingleuserOsgi {


    @ObjectClassDefinition(name = "Single User OSGI",
            description = "Single User OSGI")

    public @interface SingleConfig {

        @AttributeDefinition(
                name = "User alldetails Link",
                description = "User alldetails Link",
                type = AttributeType.STRING)
        public String getUserLinkData() default "https://reqres.in/api/users/";

        @AttributeDefinition(
                name = "User profile pic Link",
                description = "User profile pic Link",
                type = AttributeType.STRING
        )
        String getUserLinkImage() default "https://reqres.in/img/faces/";

        @AttributeDefinition(
                name = "User image Link",
                description = "User image Link",
                type = AttributeType.STRING
        )
        String getDamPath() default "/content/dam/ugamdemo";
    }

    protected String userLinkImage = "";
    protected String userLinkData = "";
    protected String dampath = "";

    @Override
    public String getUserLinkData() {
        return userLinkData;
    }

    @Override
    public String getUserLinkImage() {
        return userLinkImage;
    }

    @Override
    public String getDamPath(){ return dampath;}


    @Activate
    protected void activate(SingleConfig serviceConfig) {
        userLinkData = serviceConfig.getUserLinkData();
        userLinkImage = serviceConfig.getUserLinkImage();
        dampath = serviceConfig.getDamPath();
    }
}