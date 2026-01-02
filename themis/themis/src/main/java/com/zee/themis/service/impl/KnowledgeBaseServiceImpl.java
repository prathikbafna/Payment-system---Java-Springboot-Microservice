package com.zee.themis.service.impl;


import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.dto.Rule;
import com.zee.themis.entity.RuleEntity;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.exception.NoRuleExistsException;
import com.zee.themis.repository.RulesRepository;
import com.zee.themis.service.KnowledgeBaseService;
import com.zee.themis.service.ThemisRuleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

import static com.zee.themis.constant.ErrorConstants.NO_RULE_FOUND_ERROR;
import static com.zee.themis.dto.Rule.toRuleDto;
import static com.zee.themis.dto.Rule.toRuleEntity;

@Service
@Slf4j
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private ThemisRuleCacheService ruleNameSpaceCacheService;



    /**
     * get All Rules based on RuleName Space
     * first It will try to get it from Redis Cache
     * @param ruleNamespace
     * @return List<Rule>
     */
    public List<Rule> getAllRuleByNamespace(RuleNamespace ruleNamespace){
        List<Rule> rules = new ArrayList<>();
        log.info("getting rules by RuleNameSpace: {}",ruleNamespace);
        try {
            rules = ruleNameSpaceCacheService.getRuleByNameSpaceFromCache(ruleNamespace);
        }catch (Exception e){
            log.warn("getting Error while fetching rules from cache for ruleNameSpace: {} {}", ruleNamespace, e);
        }
        if(rules == null || rules.isEmpty()) {
            log.info("getting rules from DB for ruleNameSpace: {}", ruleNamespace);
            if (ruleNamespace == RuleNamespace.DEFAULT) {
                rules = rulesRepository.findAll().stream().filter(Objects::nonNull)
                        .map(
                                ruleEntity -> toRuleDto(ruleEntity)
                        )
                        .collect(Collectors.toList());
                saveRulesInCache(rules, ruleNamespace);
                log.info("Rules for ruleNameSpace {}, {}",ruleNamespace, rules);
                return rules;
            }
            rules = rulesRepository.findByRuleNamespace(ruleNamespace).stream().filter(Objects::nonNull)
                    .map(
                            ruleEntity -> toRuleDto(ruleEntity)
                    )
                    .collect(Collectors.toList());
            saveRulesInCache(rules, ruleNamespace);
        }
        log.info("Rules for ruleNameSpace {}, {}",ruleNamespace, rules);
        return rules;
    }

    /**
     * schedule time to clear
     * the cache for All Rules
     */
    @Scheduled(fixedDelay = 6000)
    public void evictAllCachesForPaymentRuleNameSpaceAtIntervals() {
        log.info("removing All Rules For PAYMENT NameSpace from Cache after every time interval: 10 minutes");
        ruleNameSpaceCacheService.removeRuleByNameSpaceFromCache(RuleNamespace.PAYMENT);
    }

    /**
     * adding rules in Cache
     * using RuleNameSpace as key
     * @param rules
     * @param ruleNamespace
     * @return void
     */
    public void saveRulesInCache(List<Rule> rules, RuleNamespace ruleNamespace){
        try {
            ruleNameSpaceCacheService.saveRuleByNameSpaceInCache(ruleNamespace, rules);
        }catch (Exception e){
            log.warn("getting Error while saving rules in cache for ruleNameSpace: {} {}", ruleNamespace,e);
        }
    }

    /**
     *
     * @param id
     * @return Rule
     */
    @Override
    public Rule getRuleById(String id) {
        Optional<RuleEntity> entity = rulesRepository.findById(id);
        return entity != null && entity.isPresent() ? toRuleDto(entity.get()) : null;
    }

    /**
     *
     * @param rule
     * @return Rule
     */
    @Override
    public Rule addRule(Rule rule) {
        Rule addedRule = null;
        addedRule =  toRuleDto(rulesRepository.save(toRuleEntity(rule)));
        if(addedRule !=null) {
            try {
                saveRulesInCache(Arrays.asList(addedRule), rule.getRuleNamespace());
            }catch (Exception e){
                log.warn("error while adding Rules from caches for ruleNameSpace {} {}",rule.getRuleNamespace(), addedRule );
            }
        }
        return addedRule;
    }

    @Override
    public void deleteRule(String id) {
        if(rulesRepository.existsById(id)) {
            rulesRepository.deleteById(id);
            try{
                ruleNameSpaceCacheService.removeRuleByNameSpaceFromCache(RuleNamespace.PAYMENT);
            }catch (Exception e){
                log.warn("error while removing Rules from caches");
            }
        }
        else
            throw new NoRuleExistsException(ErrorConstants.getCode(NO_RULE_FOUND_ERROR),ErrorConstants.getMessage(NO_RULE_FOUND_ERROR));
    }

}
