package com.vanthan99.batdongsan.services.ServicesImpl;

import com.vanthan99.batdongsan.entities.Post;
import com.vanthan99.batdongsan.entities.PostImage;
import com.vanthan99.batdongsan.repositories.PostImageRepository;
import com.vanthan99.batdongsan.services.PostImageService;
import com.vanthan99.batdongsan.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostImageServiceImpl implements PostImageService {
    private PostImageRepository postImageRepository;

    @Autowired
    public void setPostImageRepository(PostImageRepository postImageRepository){
        this.postImageRepository = postImageRepository;
    }

    private PostService postService;
    @Autowired
    public void setPostService(PostService postService){
        this.postService = postService;
    }


    @Override
    public List<PostImage> findAll() {
        return postImageRepository.findAll();
    }

    @Override
    public PostImage findById(Long id) {
        return postImageRepository.findById(id).orElse(null);
    }

    @Override
    public List<PostImage> findByPostId(Long id) {
        Post post = postService.findById(id);
        return post.getPostImages();
    }

    @Override
    public void save(PostImage postImage) {
        postImageRepository.save(postImage);
    }

    @Override
    public void deleteById(Long id) {
        postImageRepository.deleteById(id);
    }
}
