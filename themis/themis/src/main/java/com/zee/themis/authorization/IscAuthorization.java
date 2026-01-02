package com.zee.themis.authorization;


import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.zee.themis.constant.ApplicationConstants.THEMIS_ISC_KEY_NAME;
import static com.zee.themis.constant.ErrorConstants.UNAUTHORIZED_ERROR;



@Aspect
@Component
@Slf4j
public class IscAuthorization {

    @Value("${themis.themis_isc_key}")
    private String apiKey;

    @Before("execution(* com.zee.themis.controller.InferenceEngineController.*(..)) " +
            "|| execution(* com.zee.themis.controller.RuleEngineController.*(..)) " +
            "|| execution(* com.zee.themis.controller.InHousePaymentGatewayMetaDataController.*(..)) ")
    public void validateApiKey(JoinPoint joinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        String iscKey = request.getHeader(THEMIS_ISC_KEY_NAME);
        if (StringUtils.isEmpty(iscKey) || !iscKey.equals(apiKey)) {
            log.error("themis IscKey is invalid or not found");
            throw new UnauthorizedException(ErrorConstants.getCode(UNAUTHORIZED_ERROR),
                    ErrorConstants.getMessage(UNAUTHORIZED_ERROR));
        }
    }
}
