package com.spring.security.demo.SpringSecurityAbacDemo.config;

import com.spring.security.demo.SpringSecurityAbacDemo.policy.AuthorizationExpression;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties("auth.configurations")
@RefreshScope
@Component
public class Config {

  private List<AuthorizationExpression> rules;
}