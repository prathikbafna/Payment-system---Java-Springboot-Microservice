package com.zee.themis.service;

import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.InHousePaymentMethodRequest;


public interface InHousePaymentGatewayMetaDataService {
    InHousePaymentMethodResponse getInHousePaymentMethods(InHousePaymentMethodRequest inHousePaymentMethodRequest);
}
