package com.smosky.blogserver.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Date;


@Schema(
    name = "Response",
    description = "Schema to hold response information"
)
@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
  private Date timestamp;
  private HttpStatus status;
  private Object data;
  private Object errors;
  private String message;
  private String path;
}