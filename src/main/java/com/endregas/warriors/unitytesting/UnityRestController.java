package com.endregas.warriors.unitytesting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnityRestController {

    @GetMapping("/")
    public String greeting()
    {
        return "hello world!";
    }
}
