package com.epambootcamp.woddoc.service;

import com.epambootcamp.woddoc.dao.VideoTutorDao;
import com.epambootcamp.woddoc.model.VideoTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoTutorialServiceImpl implements VideoTutorService {
    @Autowired
    private VideoTutorDao videoTutorDao;

    @Override
    public List<VideoTutor> findAll() {
        return videoTutorDao.findAll();
    }

    @Override
    public VideoTutor findById(Long id) {
        return videoTutorDao.findById(id);
    }

    @Override
    public void save(VideoTutor videoTutor) {
        videoTutorDao.save(videoTutor);
    }

    @Override
    public void delete(VideoTutor videoTutor) {
        videoTutorDao.delete(videoTutor);
    }
}
