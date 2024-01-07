package com.smosky.blogserver.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PostsAlreadyExistException extends RuntimeException {

  public PostsAlreadyExistException(String message) {
    super(message);
  }

}