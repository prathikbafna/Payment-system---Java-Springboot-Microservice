package com.zee.themis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zee.themis.dto.PgDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "PaymentGatewayMetaData")
public class InHousePaymentGatewayMetaData {

    @Id
    private String id;

    private List<String> country;

    private String platform;

    private String tenantId;

    private List<PgDetail> pg;

}
