package com.zee.themis.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.exception.EnumValidationException;

public enum RuleNamespace {
    PAYMENT("payment"),ORDER("order"),DEFAULT("default");

    private final String type;

    RuleNamespace(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    @JsonCreator
    public static RuleNamespace create (String value) throws EnumValidationException {
        if(value == null) {
            throw new EnumValidationException(value, ApplicationConstants.RULE_NAMESPACE);
        }
        for(RuleNamespace v : values()) {
            if(value.equals(v.getType())) {
                return v;
            }
        }
        throw new EnumValidationException(value, ApplicationConstants.RULE_NAMESPACE);
    }
}