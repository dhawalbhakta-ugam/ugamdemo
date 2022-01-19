package com.ugamdemo.core.models.Impl;


        import com.ugamdemo.core.models.ThirdApi;
        import com.ugamdemo.core.utils.JSONLoaders;
        import org.apache.sling.api.resource.Resource;
        import org.apache.sling.models.annotations.DefaultInjectionStrategy;
        import org.apache.sling.models.annotations.Model;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = ThirdApi.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ThirdApiImpl implements ThirdApi {

    final Logger LOG = LoggerFactory.getLogger(ThirdApiImpl.class);
    @Inject
    String url;

    @Override
    public String getUrl(){
        return "https://reqres.in/api/users/"+url;
    }
    @Override
    public String getMessage() {

        return JSONLoaders.readJson(getUrl());
    }

}