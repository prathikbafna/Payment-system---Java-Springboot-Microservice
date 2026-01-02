package com.zee.themis.exception;

import org.springframework.stereotype.Component;

public class InvalidAddRuleRequest extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public InvalidAddRuleRequest(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public InvalidAddRuleRequest() {
    }

    public InvalidAddRuleRequest(Throwable throwable) {
        super(throwable);
    }
}
