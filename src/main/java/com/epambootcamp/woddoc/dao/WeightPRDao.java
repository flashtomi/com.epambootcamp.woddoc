package com.epambootcamp.woddoc.dao;

import com.epambootcamp.woddoc.model.WeightPR;

import java.util.List;


public interface WeightPRDao {
    List<WeightPR> findAll();

    WeightPR findById(Long id);

    void save(WeightPR weightPR);

    void delete(WeightPR weightPR);
}
