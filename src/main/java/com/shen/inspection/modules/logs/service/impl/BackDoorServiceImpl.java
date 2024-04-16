package com.shen.inspection.modules.logs.service.impl;

import com.shen.inspection.modules.logs.service.BackDoorService;
import org.springframework.stereotype.Service;

@Service
public class BackDoorServiceImpl implements BackDoorService {
    public static final String HELLO_STR = "HELLO";

    @Override
    public String printHello() {
        return HELLO_STR;
    }

}
