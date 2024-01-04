package com.smosky.blogserver.services;

import com.smosky.blogserver.model.Posts;
import org.springframework.data.domain.Pageable;

public interface IPostsService {
  /*
  * Uses: Create posts
  * */
  Posts createPosts(Posts posts);

  Posts findAll(Pageable pageable);

  Posts updatePosts(Posts posts);

  Posts deletePosts(String id);
}
