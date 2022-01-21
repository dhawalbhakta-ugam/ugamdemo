package com.ugamdemo.core.models.Impl;

import com.ugamdemo.core.models.OsgiofMultiUse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;



        import org.osgi.service.component.annotations.Activate;

        import org.osgi.service.metatype.annotations.*;

@Component(service = OsgiofMultiUse.class,immediate = true)
@Designate(ocd = OsgiofMultiUseImpl.MultipleConfig.class )
public class OsgiofMultiUseImpl implements OsgiofMultiUse {


    @ObjectClassDefinition(name = "Multiple User Config",
            description = "Multiple User Config")
    public @interface MultipleConfig {

        @AttributeDefinition(
                name = "User Data Link",
                description = "User Data Link",
                type = AttributeType.STRING)
        public String getMultipleLinkData() default "https://reqres.in/api/users?page=";

    }

    protected String userLinkData = "";

    @Override
    public String getMultipleLink() {
        return userLinkData;
    }


    @Activate
    protected void activate(MultipleConfig multipleConfig) {
        userLinkData = multipleConfig.getMultipleLinkData();
    }
}