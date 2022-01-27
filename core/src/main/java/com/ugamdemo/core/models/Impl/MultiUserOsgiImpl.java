package com.ugamdemo.core.models.Impl;

        import com.ugamdemo.core.models.MultiUserOsgi;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.metatype.annotations.*;

@Component(service = MultiUserOsgi.class,immediate = true)
@Designate(ocd = MultiUserOsgiImpl.MultipleConfig.class )
public class MultiUserOsgiImpl implements MultiUserOsgi {


    @ObjectClassDefinition(name = "Multiple User Config",
            description = "Multiple User Config")
    public @interface MultipleConfig {

        @AttributeDefinition(
                name = "User Info Link",
                description = "User Info Link",
                type = AttributeType.STRING)
        public String getMultipleLinkData() default "https://reqres.in/api/users?page=";

    }

    protected String userLinkData = "";

    @Override
    public String getMultipleLinkData() {
        return userLinkData;
    }


    @Activate
    protected void activate(MultipleConfig multipleConfig) {
        userLinkData = multipleConfig.getMultipleLinkData();
    }
}