package com.ichwan.springseries.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomClassTest {

    @Test
    void clazzTest() {
        RoomClass roomClass = new RoomClass();
        roomClass.setCode("IPA 12 A");
        roomClass.setTotalTable(40);

        Assertions.assertEquals(40, roomClass.getTotalTable());
    }
}