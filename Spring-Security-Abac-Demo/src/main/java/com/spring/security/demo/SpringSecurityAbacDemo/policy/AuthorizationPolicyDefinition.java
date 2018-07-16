package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.spring.security.demo.SpringSecurityAbacDemo.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class AuthorizationPolicyDefinition implements PolicyDefinition {

  @Autowired
  private Config fusionConfig;

  @Autowired
  private SpelDeserializer spelDeserializer;

  private List<PolicyRule> rules;

  @PostConstruct
  private void init() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    Gson gson = new Gson();
    module.addDeserializer(Expression.class, spelDeserializer);
    mapper.registerModule(module);
    try {
      PolicyRule[] rulesArray;
      rulesArray = mapper.readValue(gson.toJson(fusionConfig.getRules()), PolicyRule[].class);
      this.rules = (rulesArray != null ? Arrays.asList(rulesArray) : null);
    } catch (JsonMappingException jme) {
      log.error("An error occurred while parsing the policy file.");
    } catch (IOException ioe) {
      log.error("An error occurred while reading the policy file.");
    }
  }

  @Override
  public List<PolicyRule> getAllPolicyRules() {
    return rules;
  }

}
