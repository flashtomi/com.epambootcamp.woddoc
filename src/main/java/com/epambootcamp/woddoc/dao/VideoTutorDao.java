package com.epambootcamp.woddoc.dao;

import com.epambootcamp.woddoc.model.VideoTutor;

import java.util.List;

public interface VideoTutorDao {
    List<VideoTutor> findAll();

    VideoTutor findById(Long id);

    void save(VideoTutor videoTutor);

    void delete(VideoTutor videoTutor);
}
