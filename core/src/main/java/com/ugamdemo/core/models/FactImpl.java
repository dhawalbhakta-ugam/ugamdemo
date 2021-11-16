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
public class FactImpl {
    @ChildResource
    Resource factmulti;
    public List<Map<String, String>> getFactArea() {
        List<Map<String, String>> factAreaMap = new ArrayList<>();
        if(factmulti!=null){
            for(Resource fact : factmulti.getChildren()){
                Map<String,String> factMap = new HashMap<>();
                factMap.put("value",fact.getValueMap().get("value",String.class));
                factMap.put("text",fact.getValueMap().get("text",String.class));
                factMap.put("desc",fact.getValueMap().get("desc",String.class));
                factAreaMap.add(factMap);
            }
        }
        return factAreaMap;
    }
}