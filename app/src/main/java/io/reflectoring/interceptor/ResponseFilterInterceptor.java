package io.reflectoring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ResponseFilterInterceptor implements HandlerInterceptor {

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
        System.out.println("----> Post handle passed! v2" + response.getHeader("Content-Length"));
        response.setHeader("Content-Length", "123");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    System.out.println("----> After Completion passed!");
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }

}
