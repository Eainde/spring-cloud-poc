package com.eainde.zuul.config;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

@Configuration
public class ZuulConfigClass implements FallbackProvider {
  private static final String DEFAULT_MESSAGE = "User service is not available.";

  @Override
  public String getRoute() {
    return "*";
  }

  @Override
  public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
    if (cause instanceof HystrixTimeoutException) {
      return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
    } else {
      return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
    }
  }
}
