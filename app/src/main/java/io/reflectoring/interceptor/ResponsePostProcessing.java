package io.reflectoring.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.reflectoring.model.User;

@ControllerAdvice
public class ResponsePostProcessing implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {

        response.setStatusCode(HttpStatus.BAD_REQUEST);
        System.out.println("Passed here!!");

        if(body instanceof User) {
          User user = (User) body;
          user.setPassword("****");
          user.setEmail("AEP Spring");
          System.out.println("Yes!!");
        }
        // System.out.println(body);

        return body;
  }

}
