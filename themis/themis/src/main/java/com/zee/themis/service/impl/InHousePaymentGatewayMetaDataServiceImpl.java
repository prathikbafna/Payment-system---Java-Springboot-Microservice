package com.zee.themis.service.impl;

import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.converter.InHousePaymentMetaDataConverter;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.entity.InHousePaymentGatewayMetaData;
import com.zee.themis.exception.NoInhousePaymentExistException;
import com.zee.themis.repository.InHousePaymentGatewayMetaDataRepository;
import com.zee.themis.service.InHousePaymentGatewayMetaDataService;
import com.zee.themis.service.ThemisRuleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j
public class InHousePaymentGatewayMetaDataServiceImpl implements InHousePaymentGatewayMetaDataService {

    @Autowired
    private InHousePaymentGatewayMetaDataRepository inHousePaymentGatewayMetaDataRepository;

    @Autowired
    private InHousePaymentMetaDataConverter inHousePaymentMetaDataConverter;

    @Autowired
    private ThemisRuleCacheService themisRuleCacheService;

    @Override
    public InHousePaymentMethodResponse getInHousePaymentMethods(InHousePaymentMethodRequest inHousePaymentMethodRequest) {
        validateInhouseRequest(inHousePaymentMethodRequest);
        InHousePaymentMethodResponse inHousePaymentMethodResponse = null;
        InHousePaymentGatewayMetaData inHousePaymentGatewayMetaData = inHousePaymentGatewayMetaDataRepository.findByCountryAndPlatform(inHousePaymentMethodRequest.getCountry(),
                inHousePaymentMethodRequest.getPlatform());
        if(Objects.isNull(inHousePaymentGatewayMetaData)){
            throw new NoInhousePaymentExistException(ErrorConstants.NO_INHOUSE_PAYMENT_EXIST.getErrorCode(),ErrorConstants.NO_INHOUSE_PAYMENT_EXIST.getErrorMessage());
        }
        inHousePaymentMethodResponse = inHousePaymentMetaDataConverter.convert(inHousePaymentGatewayMetaData,null);
        if(Objects.nonNull(inHousePaymentMethodResponse)){
            saveRuleInCache(inHousePaymentMethodResponse, inHousePaymentMethodRequest);
        }
        return inHousePaymentMethodResponse;
    }

    private void saveRuleInCache(InHousePaymentMethodResponse inHousePaymentMethodResponse, InHousePaymentMethodRequest inHousePaymentMethodRequest){
        try {
            themisRuleCacheService.saveInHousePaymentGatewayInCache(inHousePaymentMethodResponse, inHousePaymentMethodRequest);
        }catch (Exception e){
            log.warn("getting Error while saving In House Payment Response in cache for country and platform: {} {} {}",inHousePaymentMethodRequest.getCountry(), inHousePaymentMethodRequest.getPlatform() ,e);
        }
    }

    private void validateInhouseRequest(InHousePaymentMethodRequest inHousePaymentMethodRequest){
        if(StringUtils.isEmpty(inHousePaymentMethodRequest.getCountry())){
            throw new NoInhousePaymentExistException(ErrorConstants.COUNTRY_CODE_MISSING.getErrorCode(),ErrorConstants.COUNTRY_CODE_MISSING.getErrorMessage());
        }
        if(StringUtils.isEmpty(inHousePaymentMethodRequest.getPlatform())){
            throw new NoInhousePaymentExistException(ErrorConstants.PLATFORM_MISSING.getErrorCode(),ErrorConstants.PLATFORM_MISSING.getErrorMessage());
        }
    }

}
