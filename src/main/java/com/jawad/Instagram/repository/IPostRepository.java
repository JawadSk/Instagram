package com.jawad.Instagram.repository;

import com.jawad.Instagram.model.Post;
import com.jawad.Instagram.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IPostRepository extends ListCrudRepository<Post, Integer> {
    List<Post> findByUser(User savedUser);
}
