package com.example.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gym.entity.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
