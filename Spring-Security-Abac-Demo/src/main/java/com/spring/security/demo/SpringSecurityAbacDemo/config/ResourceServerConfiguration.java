package com.spring.security.demo.SpringSecurityAbacDemo.config;

import com.google.gson.Gson;
import com.spring.security.demo.SpringSecurityAbacDemo.dto.ErrorDto;
import com.spring.security.demo.SpringSecurityAbacDemo.exceptions.ErrorCodes;
import com.spring.security.demo.SpringSecurityAbacDemo.policy.CustomPermissionEvaluator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@EnableResourceServer
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true
)
public class ResourceServerConfiguration implements ResourceServerConfigurer {

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources
        .authenticationManager(new OAuth2AuthenticationManager())
        .authenticationEntryPoint(this::handleException)
        .resourceId("resourceId");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .anonymous()
        .and()
        .authorizeRequests()
        .antMatchers("/user/**").permitAll()
        .anyRequest().authenticated();
  }

  private void handleException(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException authException) throws IOException {
    log.error("Authentication exception : ", authException.getMessage());

    ErrorCodes errorCode = ErrorCodes.SRV_UNAUTHORIZED;

    response.setStatus(errorCode.getHttpStatus());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setHeader("X-Data-Type", "ExceptionMessage");

    ErrorDto errorDto = new ErrorDto(errorCode.getErrorCode(), null);
    List<ErrorDto> errList = new ArrayList<>(1);
    errList.add(errorDto);
    response.getOutputStream().write(new Gson().toJson(errList).getBytes("UTF-8"));
  }

  @Bean(name = "permissionEvaluator")
  public PermissionEvaluator permissionEvaluator() {
    return new CustomPermissionEvaluator();
  }
}
