package com.zee.themis.dto;

import com.zee.themis.entity.RuleNamespace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kuldeep Gupta
 * @Date 24/06/22
 */

/**
 * Cache Dto based on RuleNameSpace
 */
@AllArgsConstructor
@Getter
@Setter
public class RuleNameSpaceCache implements Serializable {
    private RuleNamespace ruleNamespace;
    private List<Rule> rules;
}
