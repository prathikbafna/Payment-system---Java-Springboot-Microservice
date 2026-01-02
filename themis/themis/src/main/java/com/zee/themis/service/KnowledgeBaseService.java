package com.zee.themis.service;

import com.zee.themis.dto.Rule;
import com.zee.themis.entity.RuleNamespace;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface KnowledgeBaseService {
    List<Rule> getAllRuleByNamespace(RuleNamespace ruleNamespace);
    Rule getRuleById(String ruleId);
    Rule addRule(Rule rule);
    void deleteRule(String id);
}
