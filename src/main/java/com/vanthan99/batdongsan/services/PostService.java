package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Page<Post> findAll(Pageable pageable);

    Post findById(Long id);

    void save(Post post);

    void deleteById(Long id);

    Page<Post> findPostsByUserId(Long userId, Pageable pageable);

    Page<Post> findAllBySearchModal(
            Pageable pageable,
            Long categoryId,
            Long regionId,
            Long postTypeId,
            Boolean condition,
            Double area,
            Long price,
            Boolean deal,
            Long directionId,
            String keyword,
            Boolean customerType,
            String direction
    );

    Page<Post> findPostsByUser_IdAndTitleContaining(Long userId, String title, Pageable pageable);

    List<Post> findByViewCount();

    Iterable<Post> findByCategory_Id(Long categoryId);

    Iterable<Post> findByPostType_Id(Long postTypeId);

    Iterable<Post> findByRegion_Id(Long regionId);

    Page<Post> findPendingPosts(String keyword, Pageable pageable);
    Page<Post> searchApprovedPosts(String keyword, Pageable pageable);

    void updatePostViewCount(Post post);
}
