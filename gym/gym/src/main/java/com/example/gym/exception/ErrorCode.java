package com.example.gym.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_ID(1001, "Invalid ID"),
    CATEGORY_EXISTS(1002, "Category already exists"),
    EXERCISE_ALREADY_EXISTS(1003, "Exercise already exists"),
    ROLE_ALREADY_EXISTS(1004, "Role already exists"),
    USER_ALREADY_EXISTS(1005, "User already exists"),
    USER_NOT_FOUND(1006, "User not found"),
    INVALID_GOAL_NAME(1007, "Invalid goal name"),
    GOAL_ALREADY_EXISTS(1008, "Goal already exists"),
    INVALID_MEAL_TYPE(1009, "Invalid meal type"),
    NUTRITION_NOT_FOUND(1010, "Nutrition not found"),
    MEAL_NOT_FOUND(1011, "Meal not found"),
    HEALTH_DATA_NOT_FOUND(1012, "Health data not found"),
    HEALTH_DATA_EXISTS(1013, "Health data already exists"),
    WORKOUT_PLAN_NOT_FOUND(1014, "Workout plan not found"),
    TRAINER_NOT_FOUND(1015, "Trainer not found"),
    MEAL_EXIST(1016, "Meal already exists"),
    INVALID_PERIOD(1017, "Invalid period"),
    INVALID_DATE_RANGE(1018, "Invalid date range"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
