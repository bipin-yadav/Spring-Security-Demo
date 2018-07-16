package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import java.util.List;

public interface PolicyDefinition {
  List<PolicyRule> getAllPolicyRules();
}