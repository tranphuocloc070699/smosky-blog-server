package com.smosky.blogserver.repositories;

import com.smosky.blogserver.model.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PostsRepository extends JpaRepository<Posts,String> {
  Optional<Posts> findBySlug(String slug);

}
