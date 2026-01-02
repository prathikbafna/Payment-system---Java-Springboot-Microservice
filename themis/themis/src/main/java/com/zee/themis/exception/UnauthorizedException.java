package com.zee.themis.exception;

import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
public class UnauthorizedException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public UnauthorizedException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public UnauthorizedException() {
    }

    public UnauthorizedException(Throwable throwable) {
        super(throwable);
    }
}
