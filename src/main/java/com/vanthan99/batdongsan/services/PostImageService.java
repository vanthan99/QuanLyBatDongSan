package com.vanthan99.batdongsan.services;

import com.vanthan99.batdongsan.entities.PostImage;

import java.util.List;

public interface PostImageService {
    List<PostImage> findAll();
    PostImage findById(Long id);
    List<PostImage> findByPostId(Long id);
    void save(PostImage postImage);
    void deleteById(Long id);
}
