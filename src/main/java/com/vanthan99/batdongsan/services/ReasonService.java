package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Reason;

import java.util.List;

public interface ReasonService {
    void save(Reason reason);
    List<Reason> findAll();
    Reason findById(Long id);
    void delete(Long id);
}
