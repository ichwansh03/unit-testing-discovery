package com.ichwan.springseries.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClazzTest {

    @Test
    void clazzTest() {
        Clazz clazz = new Clazz();
        clazz.setCode("IPA 12 A");

    }
}