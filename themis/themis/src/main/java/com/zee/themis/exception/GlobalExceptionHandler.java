package com.zee.themis.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.dto.ValidationErrorDTO;
import com.zee.themis.response.ZeePaymentAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.zee.themis.constant.ErrorConstants.*;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<Object> defaultExceptionHandler(Exception e){
        log.error("error {}", e);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(SERVER_INTERNAL_ERROR),e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected final ResponseEntity<Object> unauthorizedExceptionHandler(UnauthorizedException e){
        log.error("error {}", e);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,e.getErrorCode(),e.getErrorMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoRuleExistsException.class)
    protected final ResponseEntity<Object> noRuleExistsExceptionHandler(NoRuleExistsException e){
        log.error("error {}", e);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(NO_RULE_FOUND_ERROR),ErrorConstants.getMessage(NO_RULE_FOUND_ERROR),HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoRuleMatchingException.class)
    protected final ResponseEntity<Object> noRuleMatchedExceptionHandler(NoRuleMatchingException e){
        log.error("error {}", e);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(NO_RULE_MATCH_ERROR),ErrorConstants.getMessage(NO_RULE_MATCH_ERROR),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected final ResponseEntity<Object> noSuchElementExceptionHandler(NoSuchElementException e){
        log.error("error {}", e);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(UNABLE_TO_FETCH_RULE_RESOLVER),ErrorConstants.getMessage(UNABLE_TO_FETCH_RULE_RESOLVER),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ValidationErrorDTO> invalidEnumExceptionHandler(HttpMessageNotReadableException e) {
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        if(e.getMostSpecificCause() instanceof EnumValidationException){
            EnumValidationException ex = (EnumValidationException)(e.getMostSpecificCause());
            ValidationErrorDTO errorDTO = new ValidationErrorDTO();
            errorDTO.setEnumName(ex.getEnumName());
            errorDTO.setEnumValue(ex.getEnumValue());
            errorDTO.setErrorMessage(ex.getEnumValue() + " is an invalid " + ex.getEnumName());
            return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(INVALID_NAMESPACE_ERROR),errorDTO.toString(),HttpStatus.BAD_REQUEST);
        }
        else if(e.getMostSpecificCause() instanceof UnrecognizedPropertyException){
            return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(INVALID_ADD_RULE_REQUEST),ErrorConstants.getMessage(INVALID_ADD_RULE_REQUEST),HttpStatus.BAD_REQUEST);
        }

        else
            return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(INVALID_AMOUNT_ERROR),ErrorConstants.getMessage(INVALID_AMOUNT_ERROR),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(INVALID_VALUES_IN_REQUEST),body.toString(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ValidationErrorDTO> invalidArgumentExceptionHandler(IllegalArgumentException e) {
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(INVALID_NAMESPACE_ERROR),e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> invalidIntegerException(NumberFormatException e) {
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(NUMBER_FORMAT_ERROR),ErrorConstants.getMessage(NUMBER_FORMAT_ERROR),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoInhousePaymentExistException.class)
    protected ResponseEntity<Object> handleNoInhousePaymentExistExceptionException(NoInhousePaymentExistException e) {
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildErrorResponse(0,e.getErrorCode(),e.getErrorMessage(),HttpStatus.BAD_REQUEST);
    }

}
