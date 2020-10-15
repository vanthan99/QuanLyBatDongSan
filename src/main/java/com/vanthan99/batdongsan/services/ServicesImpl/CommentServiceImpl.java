package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.Comment;
import com.vanthan99.batdongsan.repositories.CommentRepository;
import com.vanthan99.batdongsan.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Page<Comment> findByPostId(Long id, Pageable pageable) {
        return commentRepository.findCommentByPost_IdOrderByCreatedAtDesc(id, pageable);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }


}
