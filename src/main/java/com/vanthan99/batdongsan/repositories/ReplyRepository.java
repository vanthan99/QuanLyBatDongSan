package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    Page<Reply> findRepliesByComment_Id(Long commentId, Pageable pageable);
}
