package com.zee.themis.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Kuldeep Gupta
 * @Date 31/10/22
 */

@Slf4j
@Repository
public class InHousePaymentGatewayCacheRepository {

    public static final String THEMIS_RULE_CACHE = "themis-rule-cache#in-house-pg";
    public static final String HASH_STRING = "#";
    private RedisTemplate<String, String> inHousePaymentRedisTemplate;
    private HashOperations hashOperations;

    @Autowired
    private ObjectMapper mapper;

    public InHousePaymentGatewayCacheRepository(RedisTemplate<String, String> inHousePaymentRedisTemplate){
        this.inHousePaymentRedisTemplate = inHousePaymentRedisTemplate;
        this.hashOperations= inHousePaymentRedisTemplate.opsForHash();
    }


    /**
     * saving In house Payment gateway by using country and platform as key
     * @param inHousePaymentMethodResponse
     * @param inHousePaymentMethodRequest
     */
    public void saveInHousePaymentGateway(InHousePaymentMethodResponse inHousePaymentMethodResponse, InHousePaymentMethodRequest inHousePaymentMethodRequest){
        log.info("saving In House Payment Gateway Response as string {}", inHousePaymentMethodResponse);
        String inHousePaymentResponseString = null;
        try {
            inHousePaymentResponseString = mapper.writeValueAsString(inHousePaymentMethodResponse);
        }
        catch (Exception e){
            log.error("Error in conversion");
            return;
        }
        log.info("The converted string is {}",inHousePaymentResponseString);
        hashOperations.put(THEMIS_RULE_CACHE, generateHashKeyForRuleUsingNameSpace(inHousePaymentMethodRequest), inHousePaymentResponseString);
    }

    /**
     * generating hash key for inHouse payment gateway
     * by using country and platform
     * @param inHousePaymentMethodRequest
     * @return
     */
    private String generateHashKeyForRuleUsingNameSpace(InHousePaymentMethodRequest inHousePaymentMethodRequest){

        String hashCacheKey = StringUtils.EMPTY;
        hashCacheKey = inHousePaymentMethodRequest.getCountry() + HASH_STRING + inHousePaymentMethodRequest.getPlatform();
        log.info("Themis Rule Cache Hash key for country and platform {} {} {}", inHousePaymentMethodRequest.getCountry(), inHousePaymentMethodRequest.getPlatform(), hashCacheKey);
        return hashCacheKey;
    }
}
