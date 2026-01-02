package com.zee.themis.controller.InHousePayment;

import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.response.ZeePaymentAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


import static com.zee.themis.constant.ApplicationConstants.APPLICATION_JSON;

public interface InHousePaymentGatewayMetaDataApi {

    @Operation(summary = "fetch all in house payment methods related meta information ",
            description = "fetch all in house payment methods related meta information ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = ApplicationConstants.SUCCESS,content = @Content(mediaType = APPLICATION_JSON))
    })
    ResponseEntity<ZeePaymentAPIResponse<InHousePaymentMethodResponse>> getInhousePaymentMethods(@RequestBody InHousePaymentMethodRequest inHousePaymentMethodRequest);


    }
