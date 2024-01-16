package com.smosky.blogserver.configs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
  @Value("${eureka.client.serviceUrl.defaultZone}")
   private String eurekaUrl;


  @PostConstruct
  public void logEurekaUrl() {
    // Log the eurekaUrl when the application starts
    System.out.println("Eureka URL: " + eurekaUrl);
  }
}
