package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddPostRequest;
import com.phoenix.free.entity.Post;
import com.phoenix.free.service.PostService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@RequestMapping("/post")
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @PostMapping("/add")
    @ApiOperation(value = "发贴",response = Long.class)
    public Object addNewPost(@RequestPart("file") MultipartFile file, @RequestPart("request") AddPostRequest addPostRequest){
        Long id = sessionUtils.getUserId();
        if(!file.getOriginalFilename().isEmpty()){
            addPostRequest.setPic(file);
        }
        else{
            addPostRequest.setPic(null);
        }
        return postService.addNewPost(addPostRequest, id);
    }

    @Auth
    @GetMapping("/get/page={page}")
    @ApiOperation(value = "查看广场", response = Post.class, responseContainer = "List")
    public Object getAllPosts(@NotBlank @PathVariable("page") int page){
        AssertUtil.isTrue(page >=0, CommonErrorCode.INVALID_PARAM);
        return postService.getPosts(page);
    }

    @Auth
    @GetMapping("/get/userId={id}/page={page}")
    @ApiOperation(value = "查看某一用户发贴", response = Post.class, responseContainer = "List")
    public Object getPostsByUserId(@NotBlank @PathVariable("page") int page, @PathVariable("id") Long id){
        AssertUtil.isTrue(page >=0, CommonErrorCode.INVALID_PARAM);
        return postService.getPostByUserId(id, page);
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "查看某一贴子",response = Post.class)
    public Object getPostById(@NotBlank @PathVariable("id") Long id){
        return postService.getPostById(id);
    }

    @Admin
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "删除某一贴子",response = Integer.class)
    public Object deletePostById(@NotBlank @PathVariable("id") Long id){
        return postService.deletePostById(id);
    }

}
