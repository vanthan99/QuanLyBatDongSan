package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {
    Region findByName(String inputName);
}
