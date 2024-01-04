package com.smosky.blogserver.services;

import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostsService {
  /*
  * Uses: Create posts
  * */
  Posts createPosts(PostsDto posts);

  Page<Posts> findWithPagination(Pageable pageable);

  Posts updatePosts(Posts posts);

  Posts deletePosts(String id);
}
