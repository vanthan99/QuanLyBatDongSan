package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support,Long> {
    Page<Support> getSupportsByReason_Id(Long reasonId, Pageable pageable);
}
