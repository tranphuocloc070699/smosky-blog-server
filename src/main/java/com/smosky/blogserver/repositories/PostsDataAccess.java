package com.smosky.blogserver.repositories;

import com.smosky.blogserver.models.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostsDataAccess {
  private final PostsRepository repository;

  public Optional<Posts> findBySlug(String slug){
    return repository.findBySlug(slug);
  }
  public Optional<Posts> findByTitle(String title){
    return repository.findByTitle(title);
  }

  public Optional<Posts> findById(String id) {
    return repository.findById(id);
  }

  public Page<Posts> findAll(Pageable pageable){
    return repository.findAll(pageable);
  }

  public Posts save(Posts posts) {
    return repository.save(posts);
  }

  public void delete(String id) {
     repository.deleteById(id);
  }

}
