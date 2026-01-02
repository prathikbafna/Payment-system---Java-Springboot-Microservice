package com.zee.themis.ruleengine;


import com.zee.themis.dto.Rule;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.service.KnowledgeBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RuleEngine {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    /**
     *
     * @param inferenceEngine
     * @param inputData
     * @return
     */
    public Object run(InferenceEngine inferenceEngine, Object inputData) {
        RuleNamespace ruleNamespace = inferenceEngine.getRuleNamespace();
        List<Rule> allRulesByNamespace = knowledgeBaseService.getAllRuleByNamespace(ruleNamespace);
        Object result = inferenceEngine.run(allRulesByNamespace, inputData);
        return result;
    }

}
