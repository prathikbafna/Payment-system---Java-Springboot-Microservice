package com.zee.themis.controller;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.dto.InferenceEngineApi;
import com.zee.themis.model.payment.PaymentValidateRequest;
import com.zee.themis.model.payment.PaymentValidateResponse;
import com.zee.themis.response.ZeePaymentAPIResponse;
import com.zee.themis.ruleengine.RuleEngine;
import com.zee.themis.ruleengine.impl.PaymentInferenceEngine;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@Slf4j
@RequestMapping("${themis.api.v1}")
@SecurityRequirement(name = ApplicationConstants.THEMIS_ISC_KEY_NAME)
public class InferenceEngineController implements InferenceEngineApi {

    @Autowired
    private RuleEngine ruleEngine;

    @Autowired
    private PaymentInferenceEngine paymentInferenceEngine;

    /**
     *
     * @param paymentValidateRequest
     * @return PaymentValidateResponse
     * this method will validate rules for the payment made and will give payment details if valid payment is made
     */
    @PostMapping("${themis.api.rules}/payment")
    public ResponseEntity<ZeePaymentAPIResponse<PaymentValidateResponse>> validatePaymentRules(@Validated @RequestBody PaymentValidateRequest paymentValidateRequest){
        log.info("start payment rule validation for payment request {}",paymentValidateRequest);
        if(paymentValidateRequest != null && StringUtils.isNotBlank(paymentValidateRequest.getProviderName())){
            paymentValidateRequest.setProviderName(paymentValidateRequest.getProviderName().toUpperCase());
        }
        PaymentValidateResponse paymentValidateResponse = (PaymentValidateResponse) ruleEngine.run(paymentInferenceEngine,paymentValidateRequest);
        log.debug("payment rule validation response {}",paymentValidateResponse);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse();
        return zeePaymentAPIResponse.buildSuccessResponse(1,"payment rule validated!",paymentValidateResponse);
    }


    /**
     *
     * @param paymentValidateRequest
     * @return PaymentValidateResponse
     * this method will validate rules for the payment made and will give payment details if valid payment is made
     */
    @PostMapping("${themis.api.rules}/payment_by_country")
    public ResponseEntity<ZeePaymentAPIResponse<PaymentValidateResponse>> validatePaymentRulesByCountry(@RequestBody PaymentValidateRequest paymentValidateRequest){
        log.info("start payment rule validation for payment request {}",paymentValidateRequest);
        if(paymentValidateRequest != null && StringUtils.isNotBlank(paymentValidateRequest.getProviderName())){
            paymentValidateRequest.setProviderName(paymentValidateRequest.getProviderName().toUpperCase());
        }
        PaymentValidateResponse paymentValidateResponse = (PaymentValidateResponse) ruleEngine.run(paymentInferenceEngine,paymentValidateRequest);
        log.debug("payment rule validation response {}",paymentValidateResponse);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse();
        return zeePaymentAPIResponse.buildSuccessResponse(1,"payment rule validated!",paymentValidateResponse);
    }
}
