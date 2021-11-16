package com.ugamdemo.core.models.Impl;



import com.ugamdemo.core.models.Blog;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = Blog.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogImpl implements Blog{

    @Inject
    String heading;

    @Inject
    String desc;

    @Inject
    String img;


    @Override
    public String getBlogHeading() {
        return heading;
    }

    @Override
    public String getBlogDescription() {
        return desc;
    }

    @Override
    public String getImg() {
        return img;
    }
}
