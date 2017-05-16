package com.epambootcamp.woddoc.service;

import com.epambootcamp.woddoc.model.WeightPR;

import java.util.List;

public interface WeightPRService {
    List<WeightPR> findAll();

    WeightPR findById(Long id);

    void save(WeightPR weightPR);

    void delete(WeightPR weightPR);
}
