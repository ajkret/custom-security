package com.dersommer.custom.security.aspect;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class HttpServletRequestHolder {

  @Autowired
  private HttpServletRequest request;

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }
}
