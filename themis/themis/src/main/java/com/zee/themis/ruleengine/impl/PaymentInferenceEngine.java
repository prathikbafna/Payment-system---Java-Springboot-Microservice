package com.zee.themis.ruleengine.impl;

import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.model.payment.PaymentValidateRequest;
import com.zee.themis.model.payment.PaymentValidateResponse;
import com.zee.themis.ruleengine.InferenceEngine;
import org.springframework.stereotype.Service;


@Service
public class PaymentInferenceEngine extends InferenceEngine<PaymentValidateRequest, PaymentValidateResponse> {

    @Override
    protected PaymentValidateResponse initializeOutputResult() {
        return PaymentValidateResponse.builder().build();
    }

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.PAYMENT;
    }
}
