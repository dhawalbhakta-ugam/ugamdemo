package com.ugamdemo.core.utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;

public class ResolverUtils {
    private ResolverUtils() {

    }

    public static final String SYSTEMUSERDD = "systemuserdd";
    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException, org.apache.sling.api.resource.LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, SYSTEMUSERDD);
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
        return resolver;
    }
}
