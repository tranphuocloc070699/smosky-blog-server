package com.smosky.blogserver.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Schema(
    name = "Posts",
    description = "Schema to hold Blog information"
)
@Builder
public class PostsDto {

  @NotEmpty(message = "The thumbnail is required")
  private String thumbnail;
  @NotEmpty(message = "The title is required")
  private String title;

  @NotEmpty(message = "The slug is required")
  private String slug;

  @NotEmpty(message = "The content is required")
  private String content;

  @NotEmpty(message = "The pre_content is required")
  @JsonProperty("pre_content")
  private String preContent;

//  @Column()
//  private List<Long> upvote=   new ArrayList<>();
//
//  @Column(columnDefinition = "jsonb")
//  @Type(JsonBinaryType.class)
//  private Toc[] toc;
@JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;

//  @ManyToOne()
//  @JoinColumn(name = "user_id",nullable = false)
//  public User author;
//
//  @OneToMany(mappedBy = "story")
//  @JsonIgnoreProperties("story")
//  private List<Comment> comments;

}
