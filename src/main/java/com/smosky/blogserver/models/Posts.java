package com.smosky.blogserver.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "posts"
)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Posts {
  @Id
  @UuidGenerator
  private String id;

  @Column(nullable = false)
  private String thumbnail;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String slug;

  @Column(nullable = false)
  private String content;

  @Column(name="pre_content")
  private String preContent;

  @Column(name="created_at")
  private LocalDateTime createdAt;

  @Column(name="updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate(){
    updatedAt = LocalDateTime.now();
  }
//  @Column()
//  private List<Long> upvote=   new ArrayList<>();
//
//  @Column(columnDefinition = "jsonb")
//  @Type(JsonBinaryType.class)
//  private Toc[] toc;

//  @ManyToOne()
//  @JoinColumn(name = "user_id",nullable = false)
//  public User author;
//
//  @OneToMany(mappedBy = "story")
//  @JsonIgnoreProperties("story")
//  private List<Comment> comments;



}