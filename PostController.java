package com.myblogapp.BlogApp.controller;


import com.myblogapp.BlogApp.payload.PostDto;
import com.myblogapp.BlogApp.payload.PostResponse;
import com.myblogapp.BlogApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost (postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // get all posts rest api
    //http://localhost:8081/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo, @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize, @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy, @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {

        PostDto postById = postService.getPostById(id);

        return  ResponseEntity.ok(postById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id, @RequestBody PostDto postDto) {

        PostDto returnedPost = postService.updatePost(id,postDto);

        return  ResponseEntity.ok(returnedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) {

        postService.deletePostById(id);

        return new ResponseEntity<>("deleted", HttpStatus.GONE);
    }


}