package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

/**
 * demo实体类
 */
@Slf4j
public class DemoEntity {
    public static final String NAME = "demo";
    public static final String TIME = "12:00:00";

    public String date = "2024";

    public DemoEntity(String date) {
        this.date = date;
    }

    public DemoEntity() {

    }

    public void hello() {
//        log.info("hello this is {}",NAME);
    }

    public void getTime() {
        log.info("time is {}", TIME);
    }
}
