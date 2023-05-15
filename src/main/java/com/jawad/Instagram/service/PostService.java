package com.jawad.Instagram.service;

import com.jawad.Instagram.dto.PostInput;
import com.jawad.Instagram.dto.PostOutPut;
import com.jawad.Instagram.model.Post;
import com.jawad.Instagram.model.User;
import com.jawad.Instagram.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private UserService userService;

    public void createPost(Long id, PostInput postInput) {
        User savedUser = userService.getUser(id);

        Post post = new Post(postInput.getPostData(), savedUser);
        postRepository.save(post);
    }

    public List<PostOutPut> getAllPosts(Long id) {
        User savedUser = userService.getUser(id);
        List<Post> postList = postRepository.findByUser(savedUser);
        List<PostOutPut> outputList = new ArrayList<>();

        postList.forEach(
                post -> outputList.add(new PostOutPut(post.getPostData(), post.getCreatedDate(), post.getUpdatedDate()))
        );

        return outputList;
    }
}
