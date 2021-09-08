package com.endregas.warriors.unitytesting.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;

@Builder
@Data
public class Greeting {

    @GeneratedValue
    private Long id;

    private String message;

}
