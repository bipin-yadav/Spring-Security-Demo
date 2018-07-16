package com.spring.security.demo.SpringSecurityAbacDemo.exceptions;


public interface ErrorCode {
    /**
     * Returns error key.
     *
     * @return key
     */
    String getErrorKey();

    /**
     * Return error code.
     *
     * @return code
     */
    String getErrorCode();

    /**
     * Return Http-status.
     *
     * @return status
     */
    int getHttpStatus();

    /**
     * Returns error message.
     *
     * @return message
     */
    String getErrorMessage();
}