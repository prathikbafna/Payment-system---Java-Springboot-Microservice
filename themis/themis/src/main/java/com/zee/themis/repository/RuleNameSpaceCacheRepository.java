package com.zee.themis.repository;

import com.zee.themis.dto.Rule;
import com.zee.themis.dto.RuleNameSpaceCache;
import com.zee.themis.entity.RuleNamespace;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Kuldeep Gupta
 * @Date 24/06/22
 */

/**
 * repository layer for Redis Cache
 */
@Repository
@Slf4j
public class RuleNameSpaceCacheRepository {

    public static final String THEMIS_RULE_CACHE = "themis-rule-cache";
    public static final String HASH_STRING = "#";
    private RedisTemplate<RuleNamespace, RuleNameSpaceCache> redisTemplate;
    private HashOperations hashOperations;

    public RuleNameSpaceCacheRepository(RedisTemplate <RuleNamespace, RuleNameSpaceCache> redisTemplate){
        this.redisTemplate= redisTemplate;
        this.hashOperations= redisTemplate.opsForHash();
    }

    /**
     * saving the rule in cache
     * @param ruleNameSpaceCache
     */
    public void saveRuleUsingNameSpaceKey(RuleNameSpaceCache ruleNameSpaceCache){
        log.info("Saving Rules in Cache using RuleNameSpace as key {} {}", ruleNameSpaceCache.getRuleNamespace(), ruleNameSpaceCache.getRules());
        hashOperations.put(generateKeyForRuleUsingNameSpace(ruleNameSpaceCache.getRuleNamespace()), ruleNameSpaceCache.getRuleNamespace(), ruleNameSpaceCache);
    }

    /**
     * generating Themis Cache Key
     * by using RuleNameSpace
     * @param ruleNameSpace
     * @return
     */
    private String generateKeyForRuleUsingNameSpace(RuleNamespace ruleNameSpace){

        String themisRuleCacheKey = StringUtils.EMPTY;
        themisRuleCacheKey = THEMIS_RULE_CACHE+ HASH_STRING + ruleNameSpace;
        log.info("Themis Rule Cache key for ruleNameSpace {} {}", ruleNameSpace, themisRuleCacheKey);
        return themisRuleCacheKey;
    }

    /**
     * get rule from cache
     * using generated cache key and rule name space
     * @param ruleNameSpace
     * @return
     */
    public RuleNameSpaceCache getRuleUsingNameSpaceKey(RuleNamespace ruleNameSpace){
        log.info("Getting rule  from cache for RuleNameSpace : {}",ruleNameSpace);
        String themisRuleCacheKey = StringUtils.EMPTY;
        themisRuleCacheKey = generateKeyForRuleUsingNameSpace(ruleNameSpace);
        RuleNameSpaceCache ruleNameSpaceCache =  (RuleNameSpaceCache) hashOperations.get(themisRuleCacheKey, ruleNameSpace);
        log.info("Rule Fetched from Cache for Key and nameSpace {} {} {}", themisRuleCacheKey, ruleNameSpace, ruleNameSpaceCache);
        return ruleNameSpaceCache;
    }

    /**
     * Removing Rule from Cache
     * by Rule Name Space
     * @param ruleNameSpace
     */
    public void removeRuleByNameSpace(RuleNamespace ruleNameSpace) {
        log.info("Removing Rule from cache for RuleNameSpace : {}",ruleNameSpace);
        String themisRuleCacheKey = StringUtils.EMPTY;
        themisRuleCacheKey = generateKeyForRuleUsingNameSpace(ruleNameSpace);
        hashOperations.delete(themisRuleCacheKey, ruleNameSpace);
    }
}
