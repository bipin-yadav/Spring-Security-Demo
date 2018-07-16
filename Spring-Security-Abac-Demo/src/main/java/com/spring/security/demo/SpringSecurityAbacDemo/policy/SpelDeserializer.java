package com.spring.security.demo.SpringSecurityAbacDemo.policy;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpelDeserializer extends StdDeserializer<Expression> {

  public SpelDeserializer() {
    this(null);
  }

  protected SpelDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Expression deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    String expressionString = jp.getCodec().readValue(jp, String.class);
    SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    return spelExpressionParser.parseExpression(expressionString);
  }

}
