package com.project.omb.controller;

import com.project.omb.dto.ExerciseLogDTO;
import com.project.omb.facade.ExerciseLogFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise/log")
public class ExerciseLogController {

    private final ExerciseLogFacade exerciseLogFacade;

    @Autowired
    public ExerciseLogController(ExerciseLogFacade exerciseLogFacade) {
        this.exerciseLogFacade = exerciseLogFacade;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ExerciseLogDTO> createExerciseLog(
            @PathVariable Long userId,
            @RequestBody @Valid ExerciseLogDTO exerciseLogDTO) {
        ExerciseLogDTO createdLog = exerciseLogFacade.createExerciseLog(userId, exerciseLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLog);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ExerciseLogDTO>> getExerciseLogs(@PathVariable Long userId) {
        List<ExerciseLogDTO> logs = exerciseLogFacade.getExerciseLogs(userId);
        return ResponseEntity.ok(logs);
    }

    @PutMapping("/{userId}/{logId}")
    public ResponseEntity<ExerciseLogDTO> updateExerciseLog(
            @PathVariable Long userId,
            @PathVariable Long logId,
            @RequestBody @Valid ExerciseLogDTO exerciseLogDTO) {
        ExerciseLogDTO updatedLog = exerciseLogFacade.updateExerciseLog(userId, logId, exerciseLogDTO);
        return ResponseEntity.ok(updatedLog);
    }

    @DeleteMapping("/{userId}/{logId}")
    public ResponseEntity<Void> deleteExerciseLog(
            @PathVariable Long userId,
            @PathVariable Long logId) {
        exerciseLogFacade.deleteExerciseLog(userId, logId);
        return ResponseEntity.noContent().build();
    }
}
