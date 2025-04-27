package com.example.gym.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.gym.dto.request.TrainerRequest;
import com.example.gym.dto.request.Update.TrainerUpdateRequest;
import com.example.gym.dto.response.TrainerResponse;
import com.example.gym.entity.Trainer;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    Trainer maptoTrainer(TrainerRequest trainerRequest);

    TrainerResponse toTrainerResponse(Trainer trainer);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateTrainer(TrainerUpdateRequest trainerRequest, @MappingTarget Trainer trainer);
}
