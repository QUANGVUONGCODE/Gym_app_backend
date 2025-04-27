package com.example.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gym.dto.request.TrainerRequest;
import com.example.gym.dto.request.Update.TrainerUpdateRequest;
import com.example.gym.dto.response.TrainerResponse;
import com.example.gym.entity.Trainer;
import com.example.gym.exception.AppException;
import com.example.gym.exception.ErrorCode;
import com.example.gym.mapper.TrainerMapper;
import com.example.gym.repository.TrainerRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrainerService {
    TrainerRepository trainerRepository;
    TrainerMapper trainerMapper;

    public TrainerResponse createTrainer(TrainerRequest trainerRequest) {
        Trainer trainer = trainerMapper.maptoTrainer(trainerRequest);
        return trainerMapper.toTrainerResponse(trainerRepository.save(trainer));
    }

    public TrainerResponse updateTrainer(Long id, TrainerUpdateRequest trainerRequest) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        trainerMapper.updateTrainer(trainerRequest, trainer);
        return trainerMapper.toTrainerResponse(trainerRepository.save(trainer));
    }

    public TrainerResponse getTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        return trainerMapper.toTrainerResponse(trainer);
    }

    public List<TrainerResponse> getAllTrainers() {
        return trainerRepository.findAll().stream().map(trainerMapper::toTrainerResponse).toList();
    }

    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        trainerRepository.delete(trainer);
    }
}
