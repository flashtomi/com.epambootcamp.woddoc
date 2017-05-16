package com.epambootcamp.woddoc.service;

import com.epambootcamp.woddoc.dao.WeightPRDao;
import com.epambootcamp.woddoc.model.WeightPR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightPRServiceImpl implements WeightPRService {
    @Autowired
    WeightPRDao weightPRDao;

    @Override
    public List<WeightPR> findAll() {
        return weightPRDao.findAll();
    }

    @Override
    public WeightPR findById(Long id) {
        return weightPRDao.findById(id);
    }

    @Override
    public void save(WeightPR weightPR) {
        weightPRDao.save(weightPR);
    }

    @Override
    public void delete(WeightPR weightPR) {
        weightPRDao.delete(weightPR);

    }
}
