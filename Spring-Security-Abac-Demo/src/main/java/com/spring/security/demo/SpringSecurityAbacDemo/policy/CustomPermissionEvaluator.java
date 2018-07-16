package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import com.spring.security.demo.SpringSecurityAbacDemo.dto.Resource;
import com.spring.security.demo.SpringSecurityAbacDemo.dto.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import java.io.Serializable;

@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {

  @Autowired
  private PolicyEnforcement policy;

  @Override
  public boolean hasPermission(Authentication authentication, Object targetType, Object permission) {
    Subject subject = new Subject();
    subject.setUserId((String)targetType);

    Resource resource = new Resource();
    resource.setApi((String) permission);;

    return policy.check(subject, resource, null, null);
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId,
                               String targetType, Object permission) {
    throw new UnsupportedOperationException("Use hasPermission(#id, 'view') instead");
  }

}
