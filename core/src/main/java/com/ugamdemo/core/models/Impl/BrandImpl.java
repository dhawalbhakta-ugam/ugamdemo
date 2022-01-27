package com.ugamdemo.core.models.Impl;

import com.ugamdemo.core.models.Brand;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = Resource.class,
        adapters = Brand.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BrandImpl implements Brand{
    @Inject
    List<String> imgPath;
    @Override
    public List<String> getImgPath() {
        if(imgPath!=null)
        {
            return new ArrayList<>(imgPath);
        }
        else
        {
            return Collections.emptyList();
        }
    }
}


