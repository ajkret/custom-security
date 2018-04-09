package com.dersommer.custom.security.api;

import com.dersommer.custom.security.param.RequestDto;
import com.dersommer.custom.security.delegate.DemoDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class DemoResource {

  public static Logger LOGGER = LoggerFactory.getLogger(DemoResource.class);

  @Autowired
  private DemoDelegate delegate;

  @RequestMapping(value = "/{cid}", method = RequestMethod.GET, produces = "text/plain")
  @ResponseBody
  public String getExample(RequestDto dto) {
    return String.format("Request %s ServletRequest is %s present", dto.toString(),
        delegate.testInjectServletRequest() ? "" : "NOT");

  }

}