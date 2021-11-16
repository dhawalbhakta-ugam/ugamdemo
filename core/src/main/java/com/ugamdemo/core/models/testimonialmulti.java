package com.ugamdemo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class testimonialmulti {
    @ChildResource
    Resource testimonialmulti;
    public List<Map<String, String>> gettestimonial() {
        List<Map<String, String>> factAreaMap = new ArrayList<>();
        if(testimonialmulti!=null){
            for(Resource fact : testimonialmulti.getChildren()){
                Map<String,String> factMap = new HashMap<>();
                factMap.put("value",fact.getValueMap().get("value",String.class));
                factMap.put("text",fact.getValueMap().get("text",String.class));
                factAreaMap.add(factMap);
            }
        }
        return factAreaMap;
    }
}
