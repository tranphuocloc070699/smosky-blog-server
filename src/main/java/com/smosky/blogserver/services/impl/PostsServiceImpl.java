package com.smosky.blogserver.services.impl;

import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.model.Posts;
import com.smosky.blogserver.services.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements IPostsService {

  @Override
  public Posts createPosts(PostsDto posts) {
    return null;
  }

  @Override
  public Page<Posts> findWithPagination(Pageable pageable) {
    return null;
  }

  @Override
  public Posts updatePosts(Posts posts) {
    return null;
  }

  @Override
  public Posts deletePosts(String id) {
    return null;
  }
}
