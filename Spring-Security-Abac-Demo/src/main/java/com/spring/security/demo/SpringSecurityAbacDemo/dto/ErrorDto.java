package com.spring.security.demo.SpringSecurityAbacDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for error.
 */
@AllArgsConstructor
@Data
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
}