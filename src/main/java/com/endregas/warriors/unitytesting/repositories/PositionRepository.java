package com.endregas.warriors.unitytesting.repositories;

import com.endregas.warriors.unitytesting.model.PositionMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionMetric, Long> {
}
