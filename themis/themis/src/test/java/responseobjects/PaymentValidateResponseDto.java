package responseobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zee.themis.model.payment.PaymentValidateResponse;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentValidateResponseDto {

    @JsonProperty("data")
    public PaymentValidateResponse data;

    @JsonProperty("message")
    public String message;

    @JsonProperty("status")
    public Integer status;

    @JsonProperty("success")
    public boolean success;

    @JsonProperty("code")
    public Integer code;
}
