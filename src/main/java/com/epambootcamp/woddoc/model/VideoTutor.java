package com.epambootcamp.woddoc.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table
public class VideoTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String exercise;

    @NotNull
    //@Pattern(regexp = "^(?:(?:https?:\\/\\/)?(?:www\\.)?)?(youtube(?:-nocookie)?\\.com|youtu\\.be)\\/.*?(?:embed|e|v|watch\\?.*?v=)?\\/?([a-z0-9_-]{11})")
    private String youtubeUrl;


    public VideoTutor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }
}
