package com.example.gym.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.gym.dto.request.ConsultationRequest;
import com.example.gym.dto.request.Update.ConsultationUpdateRequest;
import com.example.gym.dto.response.ConsultationResponse;
import com.example.gym.entity.Consultation;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {
    Consultation maptoConsultation(ConsultationRequest consultationRequest);

    ConsultationResponse maptoConsultationResponse(Consultation consultation);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateConsultation(ConsultationUpdateRequest consultationRequest, @MappingTarget Consultation consultation);
}
