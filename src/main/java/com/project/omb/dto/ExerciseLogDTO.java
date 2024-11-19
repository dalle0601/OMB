package com.project.omb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseLogDTO {
    private Long id; // 수정/조회 시 사용
    private String exerciseName;
    private LocalDate date;
    private int durationMinutes;
    private int caloriesBurned;
}
