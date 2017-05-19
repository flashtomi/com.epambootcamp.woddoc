package com.epambootcamp.woddoc.web.controller;

public enum ExerciseNames {
    SNATCH("Snatch"),
    CLEAN("Clean"),
    CLEANANDJERK("Clean & Jerk"),
    DEADLIFT("Deadlift"),
    POWERSNATCH("Power Snatch"),
    POWERCLEAN("Power Clean"),
    THRUSTER("Thruster"),
    BACKSQUAT("Back Squat"),
    FRONTSQUAT("Front Squat");

    private final String name;

    ExerciseNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}