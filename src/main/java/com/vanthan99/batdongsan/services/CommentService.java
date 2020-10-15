package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    void save(Comment comment);
    Page<Comment> findAll(Pageable pageable);
    Page<Comment> findByPostId(Long id, Pageable pageable);

    Comment findById(Long id);

    void deleteById(Long id);
}
