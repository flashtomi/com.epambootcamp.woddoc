package com.epambootcamp.woddoc.service;

import com.epambootcamp.woddoc.model.VideoTutor;

import java.util.List;

public interface VideoTutorService {
    List<VideoTutor> findAll();

    VideoTutor findById(Long id);

    void save(VideoTutor videoTutor);

    void delete(VideoTutor videoTutor);
}
