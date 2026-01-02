package com.zee.themis.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
public class DefaultException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public DefaultException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public DefaultException() {
    }

    public DefaultException(Throwable throwable) {
        super(throwable);
    }
}
