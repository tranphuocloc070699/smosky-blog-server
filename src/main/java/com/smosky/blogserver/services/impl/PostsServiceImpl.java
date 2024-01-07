package com.smosky.blogserver.services.impl;

import com.smosky.blogserver.constant.PostsConstant;
import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.exceptions.ConflictException;
import com.smosky.blogserver.exceptions.PostsAlreadyExistException;
import com.smosky.blogserver.exceptions.ResourceNotFoundException;
import com.smosky.blogserver.mapper.PostsMapper;
import com.smosky.blogserver.models.Posts;
import com.smosky.blogserver.repositories.PostsDataAccess;
import com.smosky.blogserver.services.IPostsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements IPostsService {
  private final PostsDataAccess postsDataAccess;
  @Override
  public Posts createPosts(PostsDto dto) {
    Posts model = Posts.builder().build();
   PostsMapper.mapToModel(dto, model);

    Optional<Posts> postsOptional = postsDataAccess.findByTitle(dto.getTitle());
    if(postsOptional.isPresent()) {
      throw new PostsAlreadyExistException(PostsConstant.MESSAGE_409 + dto.getTitle());
    }
    return postsDataAccess.save(model);
  }

  @Override
  public Page<Posts> listByPage(int currentPage,int pageSize ,String sortBy, String sortDir,String keyword) {
    Sort sort = Sort.by(sortBy);

    sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

    return postsDataAccess.findAll( pageable);
  }

  @Override
  public Posts findPost(String slug) {
    return postsDataAccess.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("Post","Slug",slug));
  }

  @Override
  public Posts updatePosts(PostsDto dto,String id) {
    boolean isUpdated = false;
    Posts modelExisted = postsDataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
    if (!modelExisted.getContent().equals(dto.getContent())) {
      modelExisted.setContent(dto.getContent());
      isUpdated=true;
    }
    if (!modelExisted.getTitle().equals(dto.getTitle())) {
      modelExisted.setTitle(dto.getTitle());
      isUpdated=true;
    }
    if (!modelExisted.getPreContent().equals(dto.getPreContent())) {
      modelExisted.setPreContent(dto.getPreContent());
      isUpdated=true;
    }
    if (!modelExisted.getSlug().equals(dto.getSlug())) {
      modelExisted.setSlug(dto.getSlug());
      isUpdated=true;
    }
    if (!modelExisted.getThumbnail().equals(dto.getThumbnail())) {
      modelExisted.setThumbnail(dto.getThumbnail());
      isUpdated=true;
    }
   if(!isUpdated) throw new ConflictException("No update changing");

   postsDataAccess.save(modelExisted);

   return modelExisted;
  }

  @Override
  public Posts deletePosts(String id) {
    Posts modelExisted = postsDataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
    postsDataAccess.delete(id);
    return modelExisted;
  }
}
