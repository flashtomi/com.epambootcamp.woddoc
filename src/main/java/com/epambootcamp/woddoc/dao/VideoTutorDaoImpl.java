package com.epambootcamp.woddoc.dao;

import com.epambootcamp.woddoc.model.VideoTutor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


@Repository
public class VideoTutorDaoImpl implements VideoTutorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<VideoTutor> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<VideoTutor> criteria = builder.createQuery(VideoTutor.class);

        // Specify criteria root
        criteria.from(VideoTutor.class);

        // Execute query
        List<VideoTutor> videoTutors = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return videoTutors;
    }

    @Override
    public VideoTutor findById(Long id) {
        Session session = sessionFactory.openSession();
        VideoTutor videoTutor = session.get(VideoTutor.class, id);
        session.close();
        return videoTutor;
    }

    @Override
    public void save(VideoTutor videoTutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(videoTutor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(VideoTutor videoTutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(videoTutor);
        session.getTransaction().commit();
        session.close();
    }
}
