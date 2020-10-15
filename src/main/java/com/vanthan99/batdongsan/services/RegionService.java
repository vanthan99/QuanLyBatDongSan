package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {
    List<Region> findAll();

    Page<Region> findAll(Pageable pageable);

    Region findById(Long id);

    boolean save(Region region);

    void deleteById(Long id);

    Region findByName(String inputName);
}
