package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportService {
    Page<Support> findAll(Pageable pageable);

    Support findById(Long id);

    void save(Support support);

    void deleteById(Long id);

    Page<Support> findByReasonId(Long reasonId, Pageable pageable);
}
