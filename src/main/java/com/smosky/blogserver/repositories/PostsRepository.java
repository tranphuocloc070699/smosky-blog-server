package com.smosky.blogserver.repositories;

import com.smosky.blogserver.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostsRepository extends JpaRepository<Posts,String> {
  Optional<Posts> findBySlug(String slug);

  Optional<Posts> findByTitle(String title);

}
