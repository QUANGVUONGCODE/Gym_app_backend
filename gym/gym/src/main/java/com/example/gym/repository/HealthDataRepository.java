package com.example.gym.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gym.entity.HealthData;

public interface HealthDataRepository extends JpaRepository<HealthData, Long> {
    boolean existsByDate(LocalDate date);

    Optional<HealthData> findByUserId(Long userId);

    Optional<HealthData> findByUserIdAndDate(Long userId, LocalDate date);

    List<HealthData> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
