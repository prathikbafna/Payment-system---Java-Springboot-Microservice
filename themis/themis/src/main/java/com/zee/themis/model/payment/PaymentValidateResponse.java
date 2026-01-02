package com.zee.themis.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentValidateResponse {

    @JsonProperty("isValidPayment")
    private Boolean isValidPayment;

    @JsonProperty("invalidPaymentReason")
    private String invalidPaymentReason;

    @JsonProperty("minimumPaymentAmount")
    private String minimumPaymentAmount;

    @JsonProperty("gracedBillingAllowed")
    private Boolean gracedBillingAllowed;

    @JsonProperty("gracedBillingDays")
    private Integer gracedBillingDays;

    @JsonProperty("providerId")
    private String providerId;

    @JsonProperty("providerName")
    private String providerName;

    @JsonProperty("freeTrialSupported")
    private Boolean freeTrialSupported;
}
