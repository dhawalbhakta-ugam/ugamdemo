package com.ugamdemo.core.models.Impl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class TitleTextImplTest {


    private final AemContext aemContext = new AemContext();
   // private  banner;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void getText() {
    }

    @Test
    void getSectionGap() {
    }

    @Test
    void getBottomPadding() {
    }
}