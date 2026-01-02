package com.zee.themis.ruleengine;


import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.dto.Rule;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.exception.NoRuleExistsException;
import com.zee.themis.exception.NoRuleMatchingException;
import com.zee.themis.langparser.RuleParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.zee.themis.constant.ErrorConstants.NO_RULE_EXISTS_FOR_NAMESPACE_ERROR;
import static com.zee.themis.constant.ErrorConstants.NO_RULE_MATCH_ERROR;

@Slf4j
@Service
public abstract class InferenceEngine<INPUT_DATA, OUTPUT_RESULT> {

    @Autowired
    private RuleParser<INPUT_DATA, OUTPUT_RESULT> ruleParser;

    /**
     * Run inference engine on set of rules for given data.
     * @param listOfRules
     * @param inputData
     * @return
     */
    public OUTPUT_RESULT run (List<Rule> listOfRules, INPUT_DATA inputData){
        if (null == listOfRules || listOfRules.isEmpty()){
            throw new NoRuleExistsException(ErrorConstants.getCode(NO_RULE_EXISTS_FOR_NAMESPACE_ERROR),ErrorConstants.getMessage(NO_RULE_EXISTS_FOR_NAMESPACE_ERROR));
        }

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        List<Rule> conflictSet = match(listOfRules, inputData);

        //STEP 2 (RESOLVE) : Resolve the conflict and give the selected one rule.
        Rule resolvedRule = resolve(conflictSet);
        if (null == resolvedRule){
            throw new NoRuleMatchingException(ErrorConstants.getCode(NO_RULE_MATCH_ERROR),ErrorConstants.getMessage(NO_RULE_MATCH_ERROR));
        }

        //STEP 3 (EXECUTE) : Run the action of the selected rule on given data and return the output.
        OUTPUT_RESULT outputResult = executeRule(resolvedRule, inputData);

        return outputResult;
    }

    /**
     *
     * Here we are using Linear matching algorithm for pattern matching.
     * @param listOfRules
     * @param inputData
     * @return
     */
    protected List<Rule> match(List<Rule> listOfRules, INPUT_DATA inputData){
        return listOfRules.stream()
                .filter(
                        rule -> {
                            String condition = rule.getCondition();
                            return ruleParser.parseCondition(condition, inputData);
                        }
                )
                .collect(Collectors.toList());
    }

    /**
     *
     *  Here we are using find first rule logic.
     * @param conflictSet
     * @return
     */
    protected Rule resolve(List<Rule> conflictSet){
        Optional<Rule> rule = conflictSet.stream()
                .findFirst();
        if (rule.isPresent()){
            return rule.get();
        }
        return null;
    }

    /**
     * Execute selected rule on input data.
     * @param rule
     * @param inputData
     * @return
     */
    protected OUTPUT_RESULT executeRule(Rule rule, INPUT_DATA inputData){
        OUTPUT_RESULT outputResult = initializeOutputResult();
        return ruleParser.parseAction(rule.getAction(), inputData, outputResult);
    }

    protected abstract OUTPUT_RESULT initializeOutputResult();
    protected abstract RuleNamespace getRuleNamespace();
}
