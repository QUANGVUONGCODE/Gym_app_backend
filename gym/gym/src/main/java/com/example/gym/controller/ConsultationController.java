package com.example.gym.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.dto.request.ConsultationRequest;
import com.example.gym.dto.request.Update.ConsultationUpdateRequest;
import com.example.gym.dto.response.ApiResponse;
import com.example.gym.dto.response.ConsultationResponse;
import com.example.gym.service.ConsultationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("${api.prefix}/consultation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationController {
    ConsultationService consultationService;

    @PostMapping
    ApiResponse<ConsultationResponse> createConsultation(@RequestBody ConsultationRequest consultationRequest) {
        return ApiResponse.<ConsultationResponse>builder()
                .result(consultationService.createConsultation(consultationRequest))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ConsultationResponse> updateConsultation(@PathVariable Long id,
            @RequestBody ConsultationUpdateRequest consultationRequest) {
        return ApiResponse.<ConsultationResponse>builder()
                .result(consultationService.updateConsultation(id, consultationRequest))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ConsultationResponse> getConsultation(@PathVariable Long id) {
        return ApiResponse.<ConsultationResponse>builder()
                .result(consultationService.getConsultation(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ApiResponse.<String>builder()
                .result("Deleted successfully")
                .build();
    }
}
