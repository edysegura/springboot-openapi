package io.reflectoring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@WebFilter("/users")
public class AuditLogFilter extends OncePerRequestFilter {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);

    String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), "UTF-8");
    System.out.println("--->" + requestWrapper.getRequestURI());
    System.out.println("--->" + responseBody);

    if(!responseBody.equals("")) {
      ObjectNode responseJson = (ObjectNode) objectMapper.readTree(responseBody);
      // ObjectNode password = (ObjectNode) responseJson.get("password");
      // responseJson.put("password", "****");
      System.out.println(responseJson.get("password"));
    }

    responseWrapper.copyBodyToResponse();
  }

}
