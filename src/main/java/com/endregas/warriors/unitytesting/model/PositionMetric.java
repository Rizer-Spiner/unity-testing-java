package com.endregas.warriors.unitytesting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PositionMetric {
    @Id
    @GeneratedValue
    private Long id;

    private String objectTracked;
    private float xPosition;
    private float yPosition;
    private float zPosition;
    private String timestamp;

}