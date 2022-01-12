package com.ugamdemo.core.models.Impl;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import java.util.Collections;
import java.util.Map;
public class ResolveUtils {

    private ResolveUtils() {

    }
    public static final String SERVICE_USER = "project";
    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
        final Map <String, Object > param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE,  SERVICE_USER);
        ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        return resourceResolver;
    }
}