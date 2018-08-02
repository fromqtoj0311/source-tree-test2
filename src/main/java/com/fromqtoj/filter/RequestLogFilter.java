package com.fromqtoj.filter;

import com.fromqtoj.filter.io.ByteArrayServletInputStream;
import com.fromqtoj.filter.io.TeeServletOutputStream;
import com.jd.jsf.gd.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RequestLogFilter extends OncePerRequestFilter {
  private static final Logger log = LoggerFactory.getLogger(RequestLogFilter.class);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // json 输出请求体
    String requestBody = "";
    String requestContentType = request.getContentType();
    if (requestContentType != null
        && requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
      byte[] requestBodyBytes = getRequestBody(request);
      requestBody = new String(requestBodyBytes, StandardCharsets.UTF_8);
      final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBodyBytes);

      request =
          new HttpServletRequestWrapper(request) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
              return new ByteArrayServletInputStream(byteArrayInputStream);
            }
          };
    }
    if (request.getParameterMap().size() > 0) {
      String requestParams = JsonUtils.toJSONString(request.getParameterMap());

      if (requestBody.length() == 0) {
        requestBody = requestParams;
      } else {
        requestBody = "[" + requestBody + "," + requestParams + "]";
      }
    }

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    response =
        new HttpServletResponseWrapper(response) {
          @Override
          public ServletOutputStream getOutputStream() throws IOException {
            return new TeeServletOutputStream(super.getOutputStream(), byteArrayOutputStream);
          }
        };

    long begin = System.currentTimeMillis();
    try {
      filterChain.doFilter(request, response);
    } finally {
      long end = System.currentTimeMillis();
      String responseBody = "";
      String responseContentType = response.getContentType();
      // json 输出响应体
      if (responseContentType != null
          && responseContentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
        responseBody = byteArrayOutputStream.toString();
      }
      log.info(
          "requestUrl :{}, requestBody : {}, responseBody :{}, cost time :{} ms.",
          request.getRequestURI(),
          requestBody,
          responseBody,
          end - begin);
      byteArrayOutputStream.close();
    }
  }

  private byte[] getRequestBody(HttpServletRequest request) {
    int contentLength = request.getContentLength();
    if (contentLength <= 0) {
      return new byte[0];
    }
    byte[] buffer = new byte[contentLength];
    try {
      IOUtils.read(request.getInputStream(), buffer);
      return buffer;
    } catch (IOException e) {
      e.printStackTrace();
      log.error("RequestLogFilter,捕获异常:" + e.getMessage());
      return new byte[0];
    }
  }
}
