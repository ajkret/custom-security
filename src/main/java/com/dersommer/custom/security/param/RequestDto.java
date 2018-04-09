package com.dersommer.custom.security.param;

import javax.servlet.http.HttpServletRequest;

public class RequestDto {

  private String cid;
  private String ruleset;

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getRuleset() {
    return ruleset;
  }

  public void setRuleset(String ruleset) {
    this.ruleset = ruleset;
  }

  @Override
  public String toString() {
    return "RequestDto{" +
        "cid='" + cid + '\'' +
        ", ruleset='" + ruleset + '\'' +
        '}';
  }
}