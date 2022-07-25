package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddPostRequest;
import com.phoenix.free.entity.Post;

import java.util.List;

public interface PostService {
    Long addNewPost(AddPostRequest request, Long userId);

    Post getPostById(Long id);
    List<Post> getPosts(int page);
    List<Post> getPostByUserId(Long id, int page);

    int deletePostById(Long id);
}
