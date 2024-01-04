package com.smosky.blogserver.controllers;

import com.smosky.blogserver.model.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostsController {

  public ResponseEntity<Posts> createPosts() {

  }
}
