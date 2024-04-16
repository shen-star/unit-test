package com.shen.inspection.modules.logs.controller;

import com.shen.inspection.modules.logs.service.BackDoorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/backDoor", produces = MediaType.APPLICATION_JSON_VALUE)
public class BackDoorController {
    @Resource
    private BackDoorService backDoorService;

    @PostMapping("/hello")
    public String printHello() {
        return backDoorService.printHello();
    }

}
