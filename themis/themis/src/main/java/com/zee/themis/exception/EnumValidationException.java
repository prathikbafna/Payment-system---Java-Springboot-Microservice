package com.zee.themis.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class EnumValidationException extends RuntimeException {

    private String enumValue = null;
    private String enumName = null;


    public EnumValidationException(String enumValue, String enumName) {
        super(enumValue);

        this.enumValue = enumValue;
        this.enumName = enumName;
    }

    public EnumValidationException(String enumValue, String enumName, Throwable cause) {
        super(enumValue, cause);

        this.enumValue = enumValue;
        this.enumName = enumName;
    }
}
