package com.tangerinespecter.javabaseutils.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TangerineSpecter
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String Hello(@RequestParam String name) {
        return "Hello:" + name;
    }
}
