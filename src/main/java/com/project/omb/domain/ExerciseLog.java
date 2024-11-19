package com.project.omb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "EXERCISE_LOG")
public class ExerciseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 사용자와 연관
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "EXERCISE_NAME", nullable = false)
    private String exerciseName;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "DURATION_MINUTES", nullable = false)
    private int durationMinutes;

    @Column(name = "CALORIES_BURNED")
    private int caloriesBurned;
}
