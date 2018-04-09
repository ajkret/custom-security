package com.dersommer.custom.security.delegate;

import com.dersommer.custom.security.aspect.Audit;
import org.springframework.stereotype.Component;

@Component
public class DemoDelegate {

  @Audit
  public boolean testInjectServletRequest() {

    return true;
  }

}
