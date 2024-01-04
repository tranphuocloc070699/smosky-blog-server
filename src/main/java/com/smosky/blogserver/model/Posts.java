package com.smosky.blogserver.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(
    name = "posts"
)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Posts {
  @Id
  @SequenceGenerator(
      name = "posts_id_seq",
      sequenceName = "posts_id_seq",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.UUID,
      generator = "posts_id_seq"
  )
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

//  @Column()
//  private List<Long> upvote=   new ArrayList<>();
//
//  @Column(columnDefinition = "jsonb")
//  @Type(JsonBinaryType.class)
//  private Toc[] toc;

  @Column(name="created_at")
  private Date createdAt;

  @Column(name="updated_at")
  private Date updatedAt;

//  @ManyToOne()
//  @JoinColumn(name = "user_id",nullable = false)
//  public User author;
//
//  @OneToMany(mappedBy = "story")
//  @JsonIgnoreProperties("story")
//  private List<Comment> comments;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
    updatedAt = new Date();
  }

  @PreUpdate
  protected void onUpdate(){
    updatedAt = new Date();
  }

}