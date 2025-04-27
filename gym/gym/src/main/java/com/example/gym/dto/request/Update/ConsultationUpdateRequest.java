package com.example.gym.dto.request.Update;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationUpdateRequest {
    @JsonProperty("user_id")
    Long userId;

    @JsonProperty("trainer_id")
    Long trainerId;

    @JsonProperty("message")
    String message;
}
