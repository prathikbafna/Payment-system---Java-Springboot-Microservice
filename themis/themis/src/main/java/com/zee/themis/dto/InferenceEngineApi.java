package com.zee.themis.dto;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.model.payment.PaymentValidateRequest;
import com.zee.themis.model.payment.PaymentValidateResponse;
import com.zee.themis.response.ZeePaymentAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import static com.zee.themis.constant.ApplicationConstants.APPLICATION_JSON;

@Validated
public interface InferenceEngineApi {

    @Operation(summary = "validate payment based on rules",
            description = "validates given payment request by applying rules stored for payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON,schema = @Schema(implementation = PaymentValidateResponse.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<PaymentValidateResponse>> validatePaymentRules(@RequestBody PaymentValidateRequest paymentValidateRequest);


    @Operation(summary = "validate payment based on rules",
            description = "validates given payment request by applying rules stored for payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON,schema = @Schema(implementation = PaymentValidateResponse.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<PaymentValidateResponse>> validatePaymentRulesByCountry(@RequestBody PaymentValidateRequest paymentValidateRequest);

}
