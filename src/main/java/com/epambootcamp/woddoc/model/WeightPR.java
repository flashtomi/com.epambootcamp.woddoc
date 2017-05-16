package com.epambootcamp.woddoc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class WeightPR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String exerciseName;

    @NotNull
    private String weightUnit;

    @NotNull
    private int record;

    public WeightPR() {
    }

    public WeightPR(Long id, String exerciseName, String weightUnit, int record) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.weightUnit = weightUnit;
        this.record = record;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }
}


