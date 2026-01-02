package com.zee.themis.controller;


import com.zee.themis.constant.ApplicationConstants;
import com.zee.themis.constant.ErrorConstants;
import com.zee.themis.dto.Rule;
import com.zee.themis.dto.RuleEngineApi;
import com.zee.themis.entity.RuleNamespace;
import com.zee.themis.exception.NoRuleExistsException;
import com.zee.themis.response.ZeePaymentAPIResponse;
import com.zee.themis.service.KnowledgeBaseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zee.themis.constant.ErrorConstants.*;


@RestController
@Slf4j
@RequestMapping("${themis.api.v1}")
@SecurityRequirement(name = ApplicationConstants.THEMIS_ISC_KEY_NAME)
public class RuleEngineController implements RuleEngineApi {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;


    /**
     *
     * @param namespace
     * @return all rules for given namespace
     */
    @GetMapping("${themis.api.rules}")
    public ResponseEntity<ZeePaymentAPIResponse<List<Rule>>> getAllRulesByNamespace(@RequestParam("namespace") String namespace){
        log.debug("start get all rules by namespace for namespace {}",namespace);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        if(namespace == null)
            return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(EMPTY_RULE_NAMESPACE_ERROR),ErrorConstants.getMessage(EMPTY_RULE_NAMESPACE_ERROR),HttpStatus.BAD_REQUEST);
        List<Rule> rules = knowledgeBaseService.getAllRuleByNamespace(RuleNamespace.valueOf(namespace));
        if(rules == null || rules.isEmpty())
            return zeePaymentAPIResponse.buildSuccessResponse(1,ErrorConstants.getMessage(NO_RULE_EXISTS_FOR_NAMESPACE_ERROR),rules);
        log.debug("rule list fetched for namespace: {} {}",namespace,rules);
        return zeePaymentAPIResponse.buildSuccessResponse(1,"rules for namespace fetched successfully",rules);
    }

    /**
     *
     * @param id
     * @return rule for given rule id
     */
    @GetMapping("${themis.api.rules}/{id}")
    public ResponseEntity<ZeePaymentAPIResponse<Rule>> getRuleByRuleId(@PathVariable("id") String id){
        log.debug("start get rule for rule id {}",id);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        Rule rule = knowledgeBaseService.getRuleById(id);
        if(rule == null)
            return zeePaymentAPIResponse.buildErrorResponse(0,ErrorConstants.getCode(NO_RULE_FOUND_ERROR),ErrorConstants.getMessage(NO_RULE_FOUND_ERROR),HttpStatus.NOT_FOUND);
        log.debug("rule fetched for rule id: {} {}",id,rule);
        return zeePaymentAPIResponse.buildSuccessResponse(1,"rule fetched successfully for rule id: "+id,rule);
    }

    /**
     *
     * @param rule
     * @return the added rule
     */
    @PostMapping("${themis.api.rules}")
    public ResponseEntity<ZeePaymentAPIResponse<Rule>> addRule(@RequestBody Rule rule){
        log.debug("start add rule for request {}",rule);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        rule = knowledgeBaseService.addRule(rule);
        log.debug("rule added {}",rule);
        return zeePaymentAPIResponse.buildSuccessResponse(1,"rule added successfully",rule);
    }

    /**
     *
     * @param id
     * @return rule deletion response
     */
    @DeleteMapping("${themis.api.rules}/{id}")
    public ResponseEntity<ZeePaymentAPIResponse<String>> deleteRule(@PathVariable("id") String id){
        log.debug("start delete rule for rule id {}",id);
        ZeePaymentAPIResponse zeePaymentAPIResponse = new ZeePaymentAPIResponse<>();
        try {
            knowledgeBaseService.deleteRule(id);
        }
        catch (NoRuleExistsException e){
            return zeePaymentAPIResponse.buildErrorResponse(0,e.getErrorCode(),e.getErrorMessage(),HttpStatus.NOT_FOUND);
        }
        log.debug("rule deleted for id {}",id);
        return zeePaymentAPIResponse.buildSuccessResponse(1,"rule deleted successfully","rule deleted: "+id);
    }

}
