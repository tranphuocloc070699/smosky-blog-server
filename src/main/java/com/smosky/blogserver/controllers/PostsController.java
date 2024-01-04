package com.smosky.blogserver.controllers;

import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.dtos.ResponseDto;
import com.smosky.blogserver.model.Posts;
import com.smosky.blogserver.services.impl.PostsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs for Posts ",
    description = "CRUD REST APIs in Smosky to CREATE, UPDATE, FETCH AND DELETE Posts details"
)
@Controller
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostsController {

  private final PostsServiceImpl postsService;

  @Operation(
      summary = "Fetch all Posts REST API",
      description = "REST API to fetch  Posts details"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseDto.class)
          )
      )
  }
  )
  /*
   * Uses: get all posts
   * Scope:Private [ADMIN]
   * */
  @GetMapping("")
  public ResponseEntity<ResponseDto> getPostsPagination(
      @RequestParam(value="current_page",defaultValue = "0",required = false) int currentPage,
      @RequestParam(value="items_per_page",defaultValue = "2") int itemsPerPage,
      @RequestParam(value="created_at",defaultValue = "desc",required = false) String createdAt
  ) {
    Sort.Direction createdAtSort= createdAt.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    Pageable sortByCreatedAt = PageRequest.of(currentPage,
        itemsPerPage,
        Sort.by(createdAtSort,"createdAt"));

    Page<Posts> posts = postsService.findWithPagination(sortByCreatedAt);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  /*
  * Uses: create new posts
  * Scope:Private [ADMIN]
  * */
  @Operation(
      summary = "Create Post REST API",
      description = "REST API to create new Post"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "HTTP Status CREATED"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseDto.class)
          )
      )
  }
  )
  @PostMapping("")
  public ResponseEntity<ResponseDto> createPosts(@Valid PostsDto dto) {
    Posts posts = postsService.createPosts(dto);
      return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @Operation(
      summary = "Update Post Details REST API",
      description = "REST API to update Post details based on a id"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseDto.class)
          )
      )
  }
  )
  /*
   * Uses: update posts
   * Scope:Private [ADMIN]
   * */
  @PutMapping("/{slug}")
  public ResponseEntity<ResponseDto> updatePosts(@PathVariable("slug")String slug,@RequestBody Posts posts) {
     postsService.updatePosts(posts);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }



  @Operation(
      summary = "Delete Post Details REST API",
      description = "REST API to delete Post details based on a id"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "417",
          description = "Expectation Failed"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "HTTP Status Internal Server Error",
          content = @Content(
              schema = @Schema(implementation = ResponseDto.class)
          )
      )
  }
  )
  /*
   * Uses: delete posts
   * Scope:Private [ADMIN]
   * */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> deletePosts(String id) {
    Posts posts = postsService.deletePosts(id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
