package com.spring.security.demo.SpringSecurityAbacDemo.exceptions;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;


public enum ErrorCodes implements ErrorCode {

    SRV_UNAUTHORIZED(
            "keyUnAuthorized",
            "SRV_UNAUTHORIZED",
            HttpStatus.UNAUTHORIZED,
            "Not Authorized."
    ),

    GENERIC_ERROR(
            "keyGenericError",
            "SRV_GENERIC_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "There was an error while processing your request. Please try again."
    );


    private static final Map<String, ErrorCodes> map = new HashMap<>();

    static {
        for (ErrorCodes code : values()) {
            map.put(code.getErrorKey(), code);
        }
    }

    private final String errorKey;
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorCodes(String errorKey, String errorCode,
               HttpStatus httpStatus, String errorMessage) {
        this.errorKey = errorKey;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public static ErrorCodes forKey(String key) {
        return map.get(key);
    }

    @Override
    public String getErrorKey() {
        return errorKey;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus.value();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}