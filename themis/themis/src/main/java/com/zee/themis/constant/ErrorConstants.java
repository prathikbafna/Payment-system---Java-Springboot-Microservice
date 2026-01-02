package com.zee.themis.constant;


import lombok.Getter;

@Getter
public enum ErrorConstants {
    BAD_REQUEST_ERROR("THEMIS_400","bad Request the server can not process the request"),
    SERVER_INTERNAL_ERROR("THEMIS_500","Server Internal default error occurred"),
    UNAUTHORIZED_ERROR("THEMIS_401","Unauthorized Request"),
    NO_RULE_EXISTS_FOR_NAMESPACE_ERROR("THEMIS_402","No rule exists for the rule namespace"),
    NO_RULE_MATCH_ERROR("THEMIS_403","No rule matched for the input"),
    NO_RULE_FOUND_ERROR("THEMIS_404","No rule found!"),
    UNABLE_TO_ADD_RULE("THEMIS_405","Unable to add rule to database"),
    UNABLE_T0_DELETE_RULE("THEMIS_406","Unable to delete rule from database"),
    INVALID_ADD_RULE_REQUEST("THEMIS_407","Invalid rule addition request"),
    UNABLE_TO_FETCH_RULE_RESOLVER("THEMIS_408","Unable to fetch any rule resolver"),
    EMPTY_RULE_NAMESPACE_ERROR("THEMIS_409","Please pass a valid rule namespace"),
    INVALID_NAMESPACE_ERROR("THEMIS_410","Please pass a valid namespace"),
    INVALID_VALUES_IN_REQUEST("THEMIS_411","Please pass proper values in request"),
    NUMBER_FORMAT_ERROR("THEMIS_412","please pass valid integer"),
    INVALID_AMOUNT_ERROR("THEMIS_413","please pass double in amount"),
    INVALID_ADD_REQUEST("THEMIS_414","invalid add rule request"),
    NO_INHOUSE_PAYMENT_EXIST("THEMIS_415","No in house payment exist"),
    COUNTRY_CODE_MISSING("THEMIS_416","Country code is missing"),
    PLATFORM_MISSING("THEMIS_417","Platform code is missing");

    private String errorCode;
    private String errorMessage;

    ErrorConstants(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.valueOf(errorCode);
    }

    public static String getCode(ErrorConstants errorConstants) {
        for (ErrorConstants b : ErrorConstants.values()) {
            if (String.valueOf(b.errorCode).equals(errorConstants.errorCode)) {
                return b.errorCode;
            }
        }
        return null;
    }

    public static String getMessage(ErrorConstants errorConstants) {
        for (ErrorConstants b : ErrorConstants.values()) {
            if (String.valueOf(b.errorCode).equals(errorConstants.errorCode)) {
                return b.errorMessage;
            }
        }
        return null;
    }
}
