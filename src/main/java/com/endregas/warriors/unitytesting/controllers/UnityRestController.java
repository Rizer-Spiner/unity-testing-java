package com.endregas.warriors.unitytesting.controllers;

import com.endregas.warriors.unitytesting.model.Greeting;
import com.endregas.warriors.unitytesting.repositories.GreetingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnityRestController {

    private GreetingRepository greetingRepository;

    public UnityRestController(GreetingRepository greetingRepository)
    {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "hello world!";
    }

    @GetMapping("/getGreetings")
    public List<Greeting> getGreetings() {
        return greetingRepository.findAll();
    }
}
