package com.zee.themis.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZeePaymentAPIResponse<T> {

    private Boolean success;

    private int code = 0;

    private String errorCode;

    private String message;

    private T data;

    /**
     *
     * @param code
     * @param message
     * @return ZeePaymentAPIResponse<T>
     * To build an error response
     * Should be called using an empty object of T type
     */
    public ResponseEntity<ZeePaymentAPIResponse<T>> buildErrorResponse(int code, String errorCode, String message,HttpStatus httpStatus){
        this.setSuccess(false);
        this.setCode(code);
        this.setErrorCode(errorCode);
        this.setMessage(message);
        return new ResponseEntity<>(this,httpStatus);
    }

    /**
     *
     * @param code
     * @param message
     * @param data
     * @return ZeePaymentAPIResponse<T>
     * To build a success response
     * Should be called using an empty object of T type
     */
    public ResponseEntity<ZeePaymentAPIResponse<T>> buildSuccessResponse(int code, String message, T data){
        this.setSuccess(true);
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

}
