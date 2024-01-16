package com.smosky.blogserver.controllers;

import com.smosky.blogserver.constant.PostsConstant;
import com.smosky.blogserver.dtos.AppInfoConfigDto;
import com.smosky.blogserver.dtos.PostsDto;
import com.smosky.blogserver.dtos.ResponseDto;
import com.smosky.blogserver.models.Posts;
import com.smosky.blogserver.services.clients.BoilerplateFeignClient;
import com.smosky.blogserver.services.impl.PostsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Tag(
    name = "CRUD REST APIs for Posts ",
    description = "CRUD REST APIs in Smosky to CREATE, UPDATE, FETCH AND DELETE Posts details"
)
@Controller
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostsController {

  private final PostsServiceImpl postsService;
  private final HttpServletRequest httpServletRequest;
  private final AppInfoConfigDto appInfoConfigDto;
  private final BoilerplateFeignClient boilerplateFeignClient;
  @Operation(
      summary = "Fetch pagination Posts REST API",
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
      @RequestParam(value="current_page",defaultValue = "1",required = false) int currentPage,
      @RequestParam(value="page_size",defaultValue = "10") int pageSize,
      @RequestParam(value="sort_by",defaultValue = "id") String sortBy,
      @RequestParam(value="sort_dir",defaultValue = "desc",required = false) String sortDir,
      @RequestParam(value="keyword",defaultValue = "",required = false) String keyword
  ) {

    Page<Posts> posts = postsService.listByPage(currentPage,pageSize,sortBy,sortDir,keyword);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.CREATED)
        .path(httpServletRequest.getContextPath())
        .data(posts)
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @Operation(
      summary = "Fetch Single Posts REST API",
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
   * Uses: get posts
   * Scope:Private [ADMIN]
   * */
  @GetMapping("{slug}")
  public ResponseEntity<ResponseDto> findPost(
      @RequestParam(value="slug") String slug
  ) {

    Posts posts = postsService.findPost(slug);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.OK)
        .path(httpServletRequest.getContextPath())
        .data(posts)
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
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
  @PostMapping(value = "")
  public ResponseEntity<ResponseDto> createPosts(@Valid @RequestBody PostsDto dto) {
    Posts posts = postsService.createPosts(dto);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.CREATED)
        .path(httpServletRequest.getContextPath())
        .data(posts)
        .errors(null)
        .message(PostsConstant.MESSAGE_201)
        .build();
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
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
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updatePosts(@PathVariable("id")String id,@RequestBody PostsDto posts) {
     Posts model = postsService.updatePosts(posts,id);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.CREATED)
        .path(httpServletRequest.getContextPath())
        .data(model)
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
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
  public ResponseEntity<ResponseDto> deletePosts(@PathVariable("id")String id) {
    Posts model = postsService.deletePosts(id);
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.OK)
        .path(httpServletRequest.getContextPath())
        .data(model)
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }


  /*
  * Show app info
  * */
  @Operation(
      summary = "Get Contact Info",
      description = "Contact Info details that can be reached out in case of any issues"
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
  @GetMapping("/contact-info")
  public ResponseEntity<Object> getContactInfo() {
    System.out.println("This is for test blog");
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.OK)
        .path(httpServletRequest.getContextPath())
        .data(appInfoConfigDto)
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }


  /*
   * Show app info
   * */
  @Operation(
      summary = "Test Microservices FeignClient",
      description = "Get boilerplate data from boilerplate-server service"
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
  @GetMapping("/boilerplate")
  public ResponseEntity<Object> getBoilerplate() {
    ResponseDto responseDto = ResponseDto.builder()
        .timestamp(new Date())
        .status(HttpStatus.OK)
        .path(httpServletRequest.getContextPath())
        .data(boilerplateFeignClient.getCiCd())
        .errors(null)
        .message(PostsConstant.MESSAGE_200)
        .build();
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}
