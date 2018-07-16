package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.expression.Expression;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRule {
  private String name;
  private String description;
  /*
   * If evaluated to true, then this rule is applied to the request access context.
   */
  private Expression target;

  /*
   * if evaluated to true, then access granted.
   */
  private Expression condition;

  public PolicyRule(Expression target, Expression condition) {
    super();
    this.target = target;
    this.condition = condition;
  }

}
