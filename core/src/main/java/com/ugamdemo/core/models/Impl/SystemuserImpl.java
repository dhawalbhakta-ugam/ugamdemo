package com.ugamdemo.core.models.Impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.ugamdemo.core.models.Systemuser;
import com.ugamdemo.core.utils.ResolverUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Systemuser.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class SystemuserImpl implements Systemuser {


    private static final Logger LOG = LoggerFactory.getLogger(SystemuserImpl.class);

    @Inject
    private ResourceResolverFactory resourceResolverFactory;

    @Inject
    ResourceResolver resolver;
    @Inject
    QueryBuilder queryBuilder;
    @Override
    public String getUsers() {
        LOG.info("\n Printing LOGS from inside CLass");
        String listofusers = "";
        Map<String, String> map = new HashMap<>();
        map.put("path", "/home/users");
        map.put("property", "jcr:primaryType");
        map.put("property.value", "rep:User");
        map.put("p.hits", "selective");
        map.put("p.properties", "rep:principalName");
        map.put("type", "rep:User");
        try {
            ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory);

            Session session = serviceResourceResolver.adaptTo(Session.class);
            Query listQuery = queryBuilder.createQuery(PredicateGroup.create(map), session);
            SearchResult result = listQuery.getResult();
            List<Hit> hits = result.getHits();
            for(Hit hit : hits){
                listofusers = listofusers+ "\r\n" + hit.getProperties().get("rep:principalName", String.class);
            }

        }catch(Exception e){

        }
        return listofusers;
    }






}
