package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    boolean save(Category category);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findById(Long id);

    void deleteById(Long id);

    Category findByName(String inputName);
}
