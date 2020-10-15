package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findCommentByPost_IdOrderByCreatedAtDesc(Long id, Pageable pageable);
}
