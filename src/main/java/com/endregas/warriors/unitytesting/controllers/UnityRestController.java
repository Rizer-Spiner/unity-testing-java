package com.endregas.warriors.unitytesting.controllers;

import com.endregas.warriors.unitytesting.model.Greeting;
import com.endregas.warriors.unitytesting.model.PositionMetric;
import com.endregas.warriors.unitytesting.repositories.GreetingRepository;
import com.endregas.warriors.unitytesting.repositories.PositionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UnityRestController {

    private GreetingRepository greetingRepository;
    private PositionRepository positionRepository;

    public UnityRestController(GreetingRepository greetingRepository, PositionRepository positionRepository)
    {
        this.greetingRepository = greetingRepository;
        this.positionRepository = positionRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "hello world!";
    }

    @GetMapping("/getGreetings")
    public List<Greeting> getGreetings() {
        return greetingRepository.findAll();
    }

    @PostMapping("/postPosition")
    public List<PositionMetric> postPosition(PositionMetric positionMetric)
    {
        positionRepository.save(positionMetric);
        return positionRepository.findAll();
    }

    @GetMapping("/getPositions")
    public List<PositionMetric> getPositions()
    {
        return positionRepository.findAll();
    }
}
