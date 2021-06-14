package com.inspiro.data.service;


import com.inspiro.data.dao.PostRepository;
import com.inspiro.data.entity.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PostServiceImp {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findPostByUsername(String username){
        return null;
    }

    public void save(Post post){
        postRepository.save(post);
    }

    public List<Post> getAllByUserName(String user){
        return postRepository.findAllByUserUsername(user);
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }
}
