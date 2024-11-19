package com.project.omb.facade;

import com.project.omb.dto.ExerciseLogDTO;
import com.project.omb.service.ExerciseLogService;
import com.project.omb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseLogFacade {

    private final ExerciseLogService exerciseLogService;
    private final UserService userService; // 사용자 관련 로직을 위해 필요

    @Autowired
    public ExerciseLogFacade(ExerciseLogService exerciseLogService, UserService userService) {
        this.exerciseLogService = exerciseLogService;
        this.userService = userService;
    }

    public ExerciseLogDTO createExerciseLog(Long userId, ExerciseLogDTO exerciseLogDTO) {
        // 사용자 존재 확인 (추가적인 검증 처리 가능)
        userService.validateUserExistence(userId);

        // 운동 기록 생성 처리
        return exerciseLogService.createExerciseLog(userId, exerciseLogDTO);
    }

    public List<ExerciseLogDTO> getExerciseLogs(Long userId) {
        // 사용자 확인 및 운동 기록 조회
        userService.validateUserExistence(userId);
        return exerciseLogService.getExerciseLogs(userId);
    }

    public ExerciseLogDTO updateExerciseLog(Long userId, Long logId, ExerciseLogDTO exerciseLogDTO) {
        // 사용자 확인 후 운동 기록 수정
        userService.validateUserExistence(userId);
        return exerciseLogService.updateExerciseLog(logId, exerciseLogDTO);
    }

    public void deleteExerciseLog(Long userId, Long logId) {
        // 사용자 확인 후 운동 기록 삭제
        userService.validateUserExistence(userId);
        exerciseLogService.deleteExerciseLog(logId);
    }
}
