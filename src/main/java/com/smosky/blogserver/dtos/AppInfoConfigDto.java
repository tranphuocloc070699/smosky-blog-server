package com.smosky.blogserver.dtos;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "blog-server")
@Getter @Setter
public class AppInfoConfigDto {
  private String message;
  private Map<String, String> contactDetails;
  private List<String> onCallSupport;
}