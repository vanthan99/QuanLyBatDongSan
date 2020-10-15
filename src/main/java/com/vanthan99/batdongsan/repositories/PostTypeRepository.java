package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTypeRepository extends JpaRepository<PostType,Long> {
    PostType findByName(String inputName);
}
