package com.endregas.warriors.unitytesting;

import com.endregas.warriors.unitytesting.model.Greeting;
import com.endregas.warriors.unitytesting.repositories.GreetingRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnityTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnityTestingApplication.class, args);
    }


    @Bean
    ApplicationRunner applicationRunner(GreetingRepository greetingRepository)
    {
        if(greetingRepository.findAll().isEmpty())
            return args -> {
            greetingRepository.save(Greeting.builder().message("hello there").build());
            greetingRepository.save(Greeting.builder().message("good to see you").build());
            };
        else return args -> {};
    }

}
