package com.zee.themis.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoInhousePaymentExistException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public NoInhousePaymentExistException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
