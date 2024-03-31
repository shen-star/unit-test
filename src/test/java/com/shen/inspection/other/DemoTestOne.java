package com.shen.inspection.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DemoTestOne {
    @Test
    void testOne() {
        log.info("this is test one");
        System.out.println("this is a test");
        Assertions.assertTrue(true);
    }
}
