package com.ugamdemo.core.models.Impl;

import com.ugamdemo.core.models.BannerArea;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;



@Model(resourceType = BannerAreaImpl.RESOURCE_TYPE,
        adaptables = Resource.class,
        adapters = BannerArea.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerAreaImpl implements BannerArea{

    final protected static String RESOURCE_TYPE="ugamdemo/components/content/banner-area";

    @Inject
    String title;

    @Inject
    String desc;

    @Inject
    String heading;

    @Inject
    String buttonTitle;

    @Inject
    String img;

    @Inject
    String path;

    @Override
    public String getBannerAreaTitle() {
        return title;
    }

    @Override
    public String getBannerAreaHeading() {
        return heading;
    }

    @Override
    public String getBannerAreaDescription() {
        return desc;
    }

    @Override
    public String getBannerAreaButtonTitle() {
        return buttonTitle;
    }

    @Override
    public String getImg() {
        return img;
    }

    @Override
    public String getPathValue() {
        return path;
    }
}
