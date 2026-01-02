package com.zee.themis.service;

import com.zee.themis.dto.InHousePaymentMethodRequest;
import com.zee.themis.dto.InHousePaymentMethodResponse;
import com.zee.themis.dto.Rule;
import com.zee.themis.entity.RuleNamespace;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kuldeep Gupta
 * @Date 24/06/22
 */

@Service
public interface ThemisRuleCacheService {

    List<Rule> getRuleByNameSpaceFromCache(RuleNamespace ruleNameSpace);

    void saveRuleByNameSpaceInCache(RuleNamespace ruleNameSpace, List<Rule> rules);

    void removeRuleByNameSpaceFromCache(RuleNamespace ruleNameSpace);

    void saveInHousePaymentGatewayInCache(InHousePaymentMethodResponse inHousePaymentMethodResponse, InHousePaymentMethodRequest inHousePaymentMethodRequest);
}
