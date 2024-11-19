package com.project.omb.service;

import com.project.omb.domain.ExerciseLog;
import com.project.omb.domain.User;
import com.project.omb.dto.ExerciseLogDTO;
import com.project.omb.repository.ExerciseLogRepository;
import com.project.omb.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExerciseLogService {

    private final ExerciseLogRepository exerciseLogRepository;
    private final UserRepository userRepository; // 사용자 존재 확인

    @Autowired
    public ExerciseLogService(ExerciseLogRepository exerciseLogRepository, UserRepository userRepository) {
        this.exerciseLogRepository = exerciseLogRepository;
        this.userRepository = userRepository;
    }

    public ExerciseLogDTO createExerciseLog(Long userId, ExerciseLogDTO exerciseLogDTO) {
        // 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Entity 생성 및 저장
        ExerciseLog exerciseLog = ExerciseLog.builder()
                .user(user)
                .exerciseName(exerciseLogDTO.getExerciseName())
                .date(exerciseLogDTO.getDate())
                .durationMinutes(exerciseLogDTO.getDurationMinutes())
                .caloriesBurned(exerciseLogDTO.getCaloriesBurned())
                .build();

        exerciseLog = exerciseLogRepository.save(exerciseLog);

        // DTO 변환 후 반환
        return ExerciseLogDTO.builder()
                .id(exerciseLog.getId())
                .exerciseName(exerciseLog.getExerciseName())
                .date(exerciseLog.getDate())
                .durationMinutes(exerciseLog.getDurationMinutes())
                .caloriesBurned(exerciseLog.getCaloriesBurned())
                .build();
    }

    public List<ExerciseLogDTO> getExerciseLogs(Long userId) {
        // 사용자별 운동 기록 조회
        List<ExerciseLog> logs = exerciseLogRepository.findByUserId(userId);

        // DTO 변환
        return logs.stream()
                .map(log -> ExerciseLogDTO.builder()
                        .id(log.getId())
                        .exerciseName(log.getExerciseName())
                        .date(log.getDate())
                        .durationMinutes(log.getDurationMinutes())
                        .caloriesBurned(log.getCaloriesBurned())
                        .build())
                .collect(Collectors.toList());
    }

    public ExerciseLogDTO updateExerciseLog(Long logId, ExerciseLogDTO exerciseLogDTO) {
        // 기존 기록 조회
        ExerciseLog existingLog = exerciseLogRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found"));

        // 빌더를 사용해 새 상태로 객체 생성
        ExerciseLog updatedLog = existingLog.toBuilder()
                .exerciseName(exerciseLogDTO.getExerciseName())
                .date(exerciseLogDTO.getDate())
                .durationMinutes(exerciseLogDTO.getDurationMinutes())
                .caloriesBurned(exerciseLogDTO.getCaloriesBurned())
                .build();

        // 저장 후 반환
        exerciseLogRepository.save(updatedLog);

        // 저장 후 반환
        return ExerciseLogDTO.builder()
                .id(updatedLog.getId())
                .exerciseName(updatedLog.getExerciseName())
                .date(updatedLog.getDate())
                .durationMinutes(updatedLog.getDurationMinutes())
                .caloriesBurned(updatedLog.getCaloriesBurned())
                .build();
    }

    public void deleteExerciseLog(Long logId) {
        // 기록 삭제
        if (!exerciseLogRepository.existsById(logId)) {
            throw new RuntimeException("Log not found");
        }
        exerciseLogRepository.deleteById(logId);
    }
}

