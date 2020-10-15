package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Direction;

import java.util.List;

public interface DirectionService {
    void save(Direction direction);
    List<Direction> findAll();
    Direction findById(Long id);
    void deleteById(Long id);
}
