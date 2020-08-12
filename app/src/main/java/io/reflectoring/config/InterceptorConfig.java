package io.reflectoring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.reflectoring.interceptor.ResponseFilterInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Autowired
  ResponseFilterInterceptor responseFilterInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(responseFilterInterceptor);
  }
}