package com.zee.themis.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ValidationErrorDTO {
    private String enumName;
    private String enumValue;
    private String errorMessage;

    @Override
    public String toString() {
        return "invalid enum {" +
                "enumName='" + enumName + '\'' +
                ", enumValue='" + enumValue + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
