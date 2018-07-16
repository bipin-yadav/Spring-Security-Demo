package com.spring.security.demo.SpringSecurityAbacDemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
  @GetMapping(path =  "/user/ids/{id}/names/{name}")
  @PreAuthorize("hasPermission(#id, #name)")
  public ResponseEntity getOrderById(@PathVariable(name = "id") String id,
                                     @PathVariable(name = "name") String name) {
    ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
    return responseEntity;
  }
}
