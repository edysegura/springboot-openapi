package io.reflectoring.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponsePostProcessing implements ResponseBodyAdvice<Object> {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.getParameterType().equals(ResponseEntity.class);
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {

    Object processedBody = body;
    try {
      String textJson = objectMapper.writeValueAsString(body);
      System.out.println("JSON: " + textJson);
      processedBody = objectMapper.readTree(ResourceUtils.getFile("classpath:users.json"));
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

    return processedBody;
  }

}
