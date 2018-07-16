package com.spring.security.demo.SpringSecurityAbacDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(value = {"com.spring"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringSecurityAbacDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAbacDemoApplication.class, args);
	}
}
