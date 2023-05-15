package com.jawad.Instagram.controllers;

import com.jawad.Instagram.dto.PostInput;
import com.jawad.Instagram.dto.PostOutPut;
import com.jawad.Instagram.model.User;
import com.jawad.Instagram.model.UserAuthenticationToken;
import com.jawad.Instagram.service.PostService;
import com.jawad.Instagram.service.UserAuthenticationTokenService;
import com.jawad.Instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserAuthenticationTokenService authenticationTokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createPost(@RequestParam Long id, @RequestParam String token, @RequestBody PostInput postInput){
        User savedUser = userService.getUser(id);
        if (savedUser == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.BAD_REQUEST);
        }

        UserAuthenticationToken authToken = authenticationTokenService.getAuthTokenByUser(savedUser);
        if (!token.equals(authToken.getToken())){
            return new ResponseEntity<>("User token is invalid!", HttpStatus.BAD_REQUEST);
        }

        postService.createPost(id, postInput);
        return new ResponseEntity<>("Post posted successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getPost(@RequestParam Long id, @RequestParam String token){
        User savedUser = userService.getUser(id);
        if (savedUser == null){
            return new ResponseEntity<>("User does not exist!", HttpStatus.BAD_REQUEST);
        }

        UserAuthenticationToken authToken = authenticationTokenService.getAuthTokenByUser(savedUser);
        if (!token.equals(authToken.getToken())){
            return new ResponseEntity<>("User token is invalid!", HttpStatus.BAD_REQUEST);
        }

        List<PostOutPut> postOutputs = postService.getAllPosts(id);
        return new ResponseEntity<>(postOutputs, HttpStatus.FOUND);
    }
}
