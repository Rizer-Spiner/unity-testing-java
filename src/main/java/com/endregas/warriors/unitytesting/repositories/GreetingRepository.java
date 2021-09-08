package com.endregas.warriors.unitytesting.repositories;

import com.endregas.warriors.unitytesting.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
