package com.zee.themis.langparser;


import com.zee.themis.dslresolver.DSLKeywordResolver;
import com.zee.themis.dslresolver.DSLResolver;
import com.zee.themis.dslresolver.impl.PaymentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DSLParser {

    @Autowired
    private DSLPatternUtil dslPatternUtil;

    @Autowired
    private PaymentResolver paymentResolver;

    public String resolveDomainSpecificKeywords(String expression){
        Map<String, Object> dslKeywordToResolverValueMap = executeDSLResolver(expression);
        return replaceKeywordsWithValue(expression, dslKeywordToResolverValueMap);
    }

    private Map<String, Object> executeDSLResolver(String expression) {
        List<String> listOfDslKeyword = dslPatternUtil.getListOfDslKeywords(expression);
        DSLKeywordResolver dslKeywordResolver = initializeDSLKeywordResolver();
        Map<String, Object> dslKeywordToResolverValueMap = new HashMap<>();
        listOfDslKeyword.stream()
                .forEach(
                        dslKeyword -> {
                            String extractedDslKeyword = dslPatternUtil.extractKeyword(dslKeyword);
                            log.info("extracted dsl keyword for rule evaluation {}",extractedDslKeyword);
                            String keyResolver = dslPatternUtil.getKeywordResolver(extractedDslKeyword);
                            log.info("keyword resolver for rule evaluation {}",keyResolver);
                            String keywordValue = dslPatternUtil.getKeywordValue(extractedDslKeyword);
                            log.info("keyword to be fetched from config {}",keywordValue);
                            DSLResolver resolver = dslKeywordResolver.getResolver(keyResolver).get();
                            Object resolveValue = resolver.resolveValue(keywordValue);
                            log.info("resolved value for keyword {}",resolveValue);
                            dslKeywordToResolverValueMap.put(dslKeyword, resolveValue);
                        }
                );
        return dslKeywordToResolverValueMap;
    }

    private String replaceKeywordsWithValue(String expression, Map<String, Object> dslKeywordToResolverValueMap){
        List<String> keyList = dslKeywordToResolverValueMap.keySet().stream().collect(Collectors.toList());
        for (int index = 0; index < keyList.size(); index++){
            String key = keyList.get(index);
            String dslResolveValue = dslKeywordToResolverValueMap.get(key).toString();
            expression = expression.replace(key, dslResolveValue);
        }
        return expression;
    }

    private DSLKeywordResolver initializeDSLKeywordResolver(){
        return new DSLKeywordResolver(Arrays.asList(paymentResolver));
    }
}
