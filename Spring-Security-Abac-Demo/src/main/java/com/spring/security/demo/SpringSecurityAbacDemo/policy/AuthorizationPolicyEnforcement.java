

package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.EvaluationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthorizationPolicyEnforcement implements PolicyEnforcement {

  @Autowired
  @Qualifier("authorizationPolicyDefinition")
  private PolicyDefinition policyDefinition;

  @Override
  public boolean check(Object subject, Object resource, Object action, Object environment) {
    List<PolicyRule> allRules = policyDefinition.getAllPolicyRules();
    SecurityAccessContext cxt = new SecurityAccessContext(subject, resource, action, environment);
    List<PolicyRule> matchedRules = filterRules(allRules, cxt);
    return checkRules(matchedRules, cxt);
  }

  private List<PolicyRule> filterRules(List<PolicyRule> allRules, SecurityAccessContext cxt) {
    List<PolicyRule> matchedRules = new ArrayList<>();
    for (PolicyRule rule : allRules) {
      try {
        if (rule.getTarget().getValue(cxt, Boolean.class)) {
          matchedRules.add(rule);
        }
      } catch (EvaluationException ex) {
        log.info("An error occurred while evaluating PolicyRule.", ex);
      }
    }
    return matchedRules;
  }

  private boolean checkRules(List<PolicyRule> matchedRules, SecurityAccessContext cxt) {
    for (PolicyRule rule : matchedRules) {
      try {
        if (rule.getCondition().getValue(cxt, Boolean.class)) {
          return true;
        }
      } catch (EvaluationException ex) {
        log.info("An error occurred while evaluating PolicyRule.", ex);
      }
    }
    return false;
  }
}
