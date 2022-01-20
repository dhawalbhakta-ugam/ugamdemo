package com.ugamdemo.core.models.Impl;

        import com.ugamdemo.core.models.SingleUserOsgi;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.metatype.annotations.*;

@Component(service = SingleUserOsgi.class,immediate = true)
@Designate(ocd = SingleUserOsgiImpl.SingleConfig.class )
public class SingleUserOsgiImpl implements SingleUserOsgi {


    @ObjectClassDefinition(name = "Single User Configuration with osgi service",
            description = "Single User Configuration")
    public @interface SingleConfig {

        @AttributeDefinition(
                name = "User Face Link",
                description = "User Face Link",
                type = AttributeType.STRING)
        public String getUserLinkData() default "https://reqres.in/api/users/";

        @AttributeDefinition(
                name = "User Data Link",
                description = "USer Data Link",
                type = AttributeType.STRING
        )
        String getUserLinkImage() default "https://reqres.in/img/faces/";

        @AttributeDefinition(
                name = "User Data Link",
                description = "USer Data Link",
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
