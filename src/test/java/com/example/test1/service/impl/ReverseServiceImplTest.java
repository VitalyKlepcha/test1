package com.example.test1.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@SpringJUnitConfig(TestConfig.class)
public class ReverseServiceImplTest {
    private final ReverseServiceImpl reverseService;

    @Autowired
    public ReverseServiceImplTest(ReverseServiceImpl reverseService) {
        this.reverseService = reverseService;
    }

    @Test
    public void testWrite() {
        String actual = reverseService.reverse("abc");
        String expected = "cba";
        assertEquals(actual, expected);
    }
}
