package com.zee.themis.dto;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.response.ZeePaymentAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.util.List;

import static com.zee.themis.constant.ApplicationConstants.APPLICATION_JSON;

@Validated
public interface RuleEngineApi {

    @Operation(summary = "fetch all rules for a particular namespace",
            description = "fetch all rules present for particular namespace like payment , order et al")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))

    })
    ResponseEntity<ZeePaymentAPIResponse<List<Rule>>> getAllRulesByNamespace (@RequestParam("namespace") String namespace);


    @Operation(summary = "fetch rule by rule id",
            description = "fetch rule for a particular rule id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<Rule>> getRuleByRuleId(@PathVariable("id") String id);


    @Operation(summary = "add new rule",
            description = "adds a rule for a particular namespace")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<Rule>> addRule(@Parameter(description = "Create provider specific callback data for purchase/subscription",
            required=true) @Valid @RequestBody Rule rule);


    @Operation(summary = "delete rule",
            description = "delete the particular rule by rule id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "401",description = "Unauthorized request",content = @Content(mediaType = APPLICATION_JSON)),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<String>> deleteRule(@PathVariable("id") String id);

}

