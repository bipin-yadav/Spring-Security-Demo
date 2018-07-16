package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationExpression {
  private String name;
  private String description;
  private String target;
  private String condition;

}
