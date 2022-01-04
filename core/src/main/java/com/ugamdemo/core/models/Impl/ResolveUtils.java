package com.ugamdemo.core.models.Impl;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class ResolveUtils {

    private ResolveUtils() {

    }
    public static final String SERVICE_USER = "project";
    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
        final Logger LOG = LoggerFactory.getLogger(ResolveUtils.class);
        final Map <String, Object > param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE,  SERVICE_USER);
        ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        //final Map<String, Object> paramMap = new HashMap<String, Object>();
        LOG.info("\n inside ResolverUtils");
        LOG.info("\n inside ResolverUtils");
        //paramMap.put(resourceResolverFactory.SUBSERVICE, SERVICE_USER);
        //resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
        // ResourceResolver resolver = null;
        LOG.info("Map Values"+param.toString());
        LOG.info("\n inside ResolverUtils-----------"+resourceResolver);
        return resourceResolver;
    }
}