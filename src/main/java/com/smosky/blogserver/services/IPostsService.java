package com.smosky.blogserver.services;

import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.models.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostsService {
  /*
  * Uses: Create posts
  * */
  Posts createPosts(PostsDto posts);

  Page<Posts> listByPage(int pageNum, int pageSize,String sortField, String sortDir, String keyword);
  Posts findPost(String slug);

  Posts updatePosts(PostsDto posts,String id);

  Posts deletePosts(String id);
}
