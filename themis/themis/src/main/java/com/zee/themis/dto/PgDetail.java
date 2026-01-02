package com.zee.themis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PgDetail {
    private String pgId;
    private String pgName;
    private int priority ;
    private String redirectUri;
    private PaymentMethod paymentMethods;
}
