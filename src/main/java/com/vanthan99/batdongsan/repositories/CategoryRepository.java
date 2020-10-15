package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String inputName);
}
