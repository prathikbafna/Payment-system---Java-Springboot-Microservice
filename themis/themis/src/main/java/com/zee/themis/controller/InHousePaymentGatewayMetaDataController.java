package com.zee.themis.controller;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.controller.InHousePayment.InHousePaymentGatewayMetaDataApi;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.response.ZeePaymentAPIResponse;
import com.zee.themis.service.InHousePaymentGatewayMetaDataService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("${themis.api.v1}")
@SecurityRequirement(name = ApplicationConstants.THEMIS_ISC_KEY_NAME)
public class InHousePaymentGatewayMetaDataController implements InHousePaymentGatewayMetaDataApi {

    @Autowired
    private InHousePaymentGatewayMetaDataService inHousePaymentGatewayMetaDataService;


    @PostMapping("${themis.api.pg-meta-data}")
    @Override
    public ResponseEntity<ZeePaymentAPIResponse<InHousePaymentMethodResponse>> getInhousePaymentMethods(InHousePaymentMethodRequest inHousePaymentMethodRequest) {
        InHousePaymentMethodResponse inHousePaymentMethodResponse = inHousePaymentGatewayMetaDataService.getInHousePaymentMethods(inHousePaymentMethodRequest);
        ZeePaymentAPIResponse<InHousePaymentMethodResponse> zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        return zeePaymentAPIResponse.buildSuccessResponse(200,"success",inHousePaymentMethodResponse);
    }
}
