package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityAccessContext {

  private Object subject;
  private Object resource;
  private Object action;
  private Object environment;

}
