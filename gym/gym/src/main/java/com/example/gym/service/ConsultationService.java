package com.example.gym.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.gym.dto.request.ConsultationRequest;
import com.example.gym.dto.request.Update.ConsultationUpdateRequest;
import com.example.gym.dto.response.ConsultationResponse;
import com.example.gym.entity.Consultation;
import com.example.gym.entity.Trainer;
import com.example.gym.entity.User;
import com.example.gym.exception.AppException;
import com.example.gym.exception.ErrorCode;
import com.example.gym.mapper.ConsultationMapper;
import com.example.gym.repository.ConsultationRepository;
import com.example.gym.repository.TrainerRepository;
import com.example.gym.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationService {
    ConsultationRepository consultationRepository;
    UserRepository userRepository;
    TrainerRepository trainerRepository;
    ConsultationMapper consultationMapper;

    public ConsultationResponse createConsultation(ConsultationRequest consultationRequest) {
        User user = userRepository.findById(consultationRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Trainer trainer = trainerRepository.findById(consultationRequest.getTrainerId())
                .orElseThrow(() -> new AppException(ErrorCode.TRAINER_NOT_FOUND));

        Consultation consultation = consultationMapper.maptoConsultation(consultationRequest);
        consultation.setUser(user);
        consultation.setTrainer(trainer);
        consultation.setDate(LocalDate.now());
        return consultationMapper.maptoConsultationResponse(consultationRepository.save(consultation));
    }

    public ConsultationResponse getConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        return consultationMapper.maptoConsultationResponse(consultation);
    }

    public ConsultationResponse updateConsultation(Long id, ConsultationUpdateRequest consultationRequest) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        consultationMapper.updateConsultation(consultationRequest, consultation);
        return consultationMapper.maptoConsultationResponse(consultationRepository.save(consultation));
    }

    public void deleteConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        consultationRepository.delete(consultation);
    }
}
