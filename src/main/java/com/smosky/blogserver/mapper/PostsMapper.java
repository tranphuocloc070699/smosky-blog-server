package com.smosky.blogserver.mapper;

import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.models.Posts;

public class PostsMapper {
  public static PostsDto mapToDto(Posts model,PostsDto dto) {
    dto.setTitle(model.getTitle());
    dto.setSlug(model.getSlug());
    dto.setContent(model.getContent());
    dto.setThumbnail(model.getThumbnail());
    dto.setPreContent(model.getPreContent());
    dto.setCreatedAt(model.getCreatedAt());
    dto.setUpdatedAt(model.getUpdatedAt());
    return dto;
  }

  public static Posts mapToModel(PostsDto dto,Posts model) {

    model.setTitle(dto.getTitle());
    model.setSlug(dto.getSlug());
    model.setContent(dto.getContent());
    model.setThumbnail(dto.getThumbnail());
    model.setPreContent(dto.getPreContent());
    return model;

  }
}
