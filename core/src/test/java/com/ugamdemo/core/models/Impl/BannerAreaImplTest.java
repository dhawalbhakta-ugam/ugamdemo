package com.ugamdemo.core.models.Impl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BannerAreaImplTest {

    private final AemContext aemContext = new AemContext();
    private BannerAreaImpl banner;

    @BeforeEach
    void setUp() {
        aemContext.load().json("/Banner.json", "/content");
    }

    @Test
    void getBannerAreaTitle() {
        Resource json = aemContext.currentResource("/content/Author");
        BannerAreaImpl bannerarea = json.adaptTo(BannerAreaImpl.class);
        assertEquals("THIS IS ME", bannerarea.getBannerAreaTitle());
    }

   /* @Test
    void getBannerAreaHeading() {
    }

    @Test
    void getBannerAreaDescription() {
    }

    @Test
    void getBannerAreaButtonTitle() {
    }

    @Test
    void getImg() {
    }

    @Test
    void getPathValue() {
    } */
}