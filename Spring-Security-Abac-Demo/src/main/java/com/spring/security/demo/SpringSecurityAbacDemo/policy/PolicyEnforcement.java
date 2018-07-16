package com.spring.security.demo.SpringSecurityAbacDemo.policy;

public interface PolicyEnforcement {

  boolean check(Object subject, Object resource, Object action, Object environment);

}