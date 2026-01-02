package com.zee.themis.model.payment;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class PaymentValidateRequest {

    @NotEmpty(message = "provider name can not be null/empty")
    private String providerName;

    @NotEmpty(message = "country can not be null/empty")
    private String country;

    @NotNull(message = "amount can not be null")
    private Double amount;

    @NotEmpty(message = "merchant account can not be null/empty")
    private String merchantAccount;
}
