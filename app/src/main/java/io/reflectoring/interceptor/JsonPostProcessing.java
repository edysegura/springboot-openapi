package io.reflectoring.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

@ControllerAdvice
public class JsonPostProcessing extends JsonViewResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    System.out.println("Passed here!");
    return returnType.getParameterType().equals(ResponseEntity.class);
  }

  @Override
  protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
      MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
    System.out.println("---> json view: " +  bodyContainer);
    super.beforeBodyWriteInternal(bodyContainer, contentType, returnType, request, response);
  }
}
