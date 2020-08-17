package io.reflectoring.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponsePostProcessing implements ResponseBodyAdvice<String> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    System.out.println("---> advice: " + returnType.getParameterType());
    return returnType.getParameterType().equals(String.class);
  }

  @Override
  public String beforeBodyWrite(String body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    System.out.println("---> advice: " + returnType.getParameterType());
    return body.toUpperCase();
  }

}
