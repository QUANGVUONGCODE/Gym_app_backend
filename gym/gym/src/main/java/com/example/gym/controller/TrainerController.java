package com.example.gym.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gym.dto.request.TrainerRequest;
import com.example.gym.dto.request.Update.TrainerUpdateRequest;
import com.example.gym.dto.response.ApiResponse;
import com.example.gym.dto.response.TrainerResponse;
import com.example.gym.service.TrainerService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("${api.prefix}/trainer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrainerController {
    TrainerService trainerService;

    @PostMapping
    ApiResponse<TrainerResponse> createTrainer(@RequestBody TrainerRequest trainerRequest) {
        return ApiResponse.<TrainerResponse>builder()
                .result(trainerService.createTrainer(trainerRequest))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<TrainerResponse> updateTrainer(@PathVariable Long id,
            @RequestBody TrainerUpdateRequest trainerRequest) {
        return ApiResponse.<TrainerResponse>builder()
                .result(trainerService.updateTrainer(id, trainerRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<TrainerResponse>> getAllTrainers() {
        return ApiResponse.<List<TrainerResponse>>builder()
                .result(trainerService.getAllTrainers())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<TrainerResponse> getTrainer(@PathVariable Long id) {
        return ApiResponse.<TrainerResponse>builder()
                .result(trainerService.getTrainer(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ApiResponse.<String>builder()
                .result("Deleted successfully")
                .build();
    }
}
