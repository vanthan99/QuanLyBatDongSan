package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.Direction;
import com.vanthan99.batdongsan.repositories.DirectionRepository;
import com.vanthan99.batdongsan.services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DirectionServiceImpl implements DirectionService {
    private DirectionRepository directionRepository;

    @Autowired
    public void setDirectionRepository(DirectionRepository directionRepository){
        this.directionRepository = directionRepository;
    }

    @Override
    public void save(Direction direction) {
        directionRepository.save(direction);
    }

    @Override
    public List<Direction> findAll() {
        return directionRepository.findAll();
    }

    @Override
    public Direction findById(Long id) {
        return directionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        directionRepository.deleteById(id);
    }
}
