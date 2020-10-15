package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyService {
    Page<Reply> findAll(Pageable pageable);

    Page<Reply> findRepliesByComment(Long commentId, Pageable pageable);

    Reply findById(Long id);

    void save(Reply reply);

    void deleteById(Long id);
}
