package com.smosky.blogserver.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("boilerplate-server")
public interface BoilerplateFeignClient {
  @GetMapping(value = "/spring/ci-cd",consumes = "application/json")
  public Object getCiCd(@RequestHeader("X-CORRELATION-ID")
                          String correlationId);
}
