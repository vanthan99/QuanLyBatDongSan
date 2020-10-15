package com.vanthan99.batdongsan.repositories;

import com.vanthan99.batdongsan.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> getPostsByUser_Id(Long userId, Pageable pageable);

    Page<Post> getPostsByUser_IdAndTitleContaining(Long userId,String title,Pageable pageable);

    @Query(value = "SELECT * FROM bat_dong_san.post order by view_count desc, created_at asc limit 10",
            nativeQuery = true)
    List<Post> findByViewCount();

    Iterable<Post> findByCategory_Id(Long categoryId);

    Iterable<Post> findByPostType_Id(Long postTypeId);

    Iterable<Post> findByRegion_Id(Long regionId);
}
