package org.tasos.proj2.onionarchitecture.springapplication;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
        System.out.println("🔍 Incoming request: " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("🔍 Headers: " + Collections.list(request.getHeaderNames())
          .stream()
          .map(h -> h + ": " + request.getHeader(h))
          .collect(Collectors.joining(", ")));
        filterChain.doFilter(request, response);
    }
}
