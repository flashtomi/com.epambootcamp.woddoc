package com.epambootcamp.woddoc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.epambootcamp.woddoc.model.WeightPR;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


@Repository
public class WeightPRDaoImpl implements WeightPRDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<WeightPR> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<WeightPR> criteria = builder.createQuery(WeightPR.class);

        // Specify criteria root
        criteria.from(WeightPR.class);

        // Execute query
        List<WeightPR> weightPRs = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return weightPRs;
    }

    @Override
    public WeightPR findById(Long id) {
        Session session = sessionFactory.openSession();
        WeightPR weightPR = session.get(WeightPR.class, id);
        session.close();

        return weightPR;
    }

    @Override
    public void save(WeightPR weightPR) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(weightPR);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(WeightPR weightPR) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(weightPR);
        session.getTransaction().commit();
        session.close();
    }


}
