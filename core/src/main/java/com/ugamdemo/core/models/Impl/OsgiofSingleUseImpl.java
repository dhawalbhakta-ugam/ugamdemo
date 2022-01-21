package com.ugamdemo.core.models.Impl;

        import com.ugamdemo.core.models.OsgiofSingleUse;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.metatype.annotations.*;

@Component(service = OsgiofSingleUse.class,immediate = true)
@Designate(ocd = OsgiofSingleUseImpl.SingleConfig.class )
public class OsgiofSingleUseImpl implements OsgiofSingleUse {


    @ObjectClassDefinition(name = "Single User Configuration with osgi service",
            description = "Single User Configuration")
    public @interface SingleConfig {

        @AttributeDefinition(
                name = "user data to display",
                description = "User data to display",
                type = AttributeType.STRING)
        public String getUserLinkData() default "https://reqres.in/api/users/";

        @AttributeDefinition(
                name = "user link to display ",
                description = "user link to display",
                type = AttributeType.STRING
        )
        String getUserLinkImage() default "https://reqres.in/img/faces/";

        @AttributeDefinition(
                name = " user dam to display",
                description = " user dam to display",
                type = AttributeType.STRING
        )
        String getDamPathimage() default "/content/dam/ugamdemo/";
    }

    protected String getLinkData = "";
    protected String getLinkImage = "";
    protected String getDamPathimage = "";

    @Override
    public String getLinkData() {
        return getLinkData;
    }

    @Override
    public String getLinkImage() {
        return getLinkImage;
    }

    @Override
    public String getDamPathimage(){ return getDamPathimage;}


    @Activate
    protected void activate(SingleConfig serviceConfig) {
        getLinkData = serviceConfig.getUserLinkData();
        getLinkImage = serviceConfig.getUserLinkImage();
        getDamPathimage = serviceConfig.getDamPathimage();
    }
}
