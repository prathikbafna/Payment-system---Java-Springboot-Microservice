package com.zee.themis.service.impl;

import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.Rule;
import com.zee.themis.dto.RuleNameSpaceCache;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.repository.InHousePaymentGatewayCacheRepository;
import com.zee.themis.repository.RuleNameSpaceCacheRepository;
import com.zee.themis.service.ThemisRuleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kuldeep Gupta
 * @Date 24/06/22
 */

@Service
@Slf4j
public class ThemisRuleCacheServiceImpl implements ThemisRuleCacheService {

    @Autowired
    private RuleNameSpaceCacheRepository nameSpaceCacheRepository;

    @Autowired
    private InHousePaymentGatewayCacheRepository inHousePaymentGatewayCacheRepository;

    @Override
    public List<Rule> getRuleByNameSpaceFromCache(RuleNamespace ruleNameSpace) {
        List<Rule> ruleList = new ArrayList<>();
        RuleNameSpaceCache ruleNameSpaceCache = nameSpaceCacheRepository.getRuleUsingNameSpaceKey(ruleNameSpace);
        if(ruleNameSpaceCache != null){
            ruleList = ruleNameSpaceCache.getRules();
        }
         return ruleList;
    }

    @Override
    public void saveRuleByNameSpaceInCache(RuleNamespace ruleNameSpace, List<Rule> rules) {
        RuleNameSpaceCache ruleNameSpaceCache = new RuleNameSpaceCache(ruleNameSpace, rules);
        nameSpaceCacheRepository.saveRuleUsingNameSpaceKey(ruleNameSpaceCache);
    }

    @Override
    public void removeRuleByNameSpaceFromCache(RuleNamespace ruleNameSpace) {
        nameSpaceCacheRepository.removeRuleByNameSpace(ruleNameSpace);
    }

    @Override
    public void saveInHousePaymentGatewayInCache(InHousePaymentMethodResponse inHousePaymentMethodResponse, InHousePaymentMethodRequest inHousePaymentMethodRequest){
        inHousePaymentGatewayCacheRepository.saveInHousePaymentGateway(inHousePaymentMethodResponse, inHousePaymentMethodRequest);
    }
}
