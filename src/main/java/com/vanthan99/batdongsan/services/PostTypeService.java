package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostTypeService {
    List<PostType> findAll();

    Page<PostType> findAll(Pageable pageable);

    PostType findById(Long id);

    boolean save(PostType postType);

    void deleteById(Long id);

    PostType findByName(String inputName);
}
