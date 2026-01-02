package com.zee.themis.dto;


import com.zee.themis.entity.RuleEntity;
import com.zee.themis.entity.RuleNamespace;
import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class Rule implements Serializable {

    @NotNull(message = "rule namespace can not be blank")
    private RuleNamespace ruleNamespace;

    @NotEmpty(message = "condition can not be blank")
    private String condition;

    @NotEmpty(message = "action can not be blank")
    private String action;

    @NotEmpty(message = "priority must not be empty")
    @Pattern(regexp = "\\d+",message = "priority must be integer value")
    private String priority;

    @NotEmpty(message = "description can not be blank")
    private String description;

    public static Rule toRuleDto(RuleEntity ruleEntity){
        return Rule.builder()
                .ruleNamespace(ruleEntity.getRuleNamespace())
                .condition(ruleEntity.getCondition())
                .action(ruleEntity.getAction())
                .description(ruleEntity.getDescription())
                .priority(String.valueOf(ruleEntity.getPriority()))
                .build();
    }

    public static RuleEntity toRuleEntity(Rule rule){
        return RuleEntity.builder()
                .ruleNamespace(rule.getRuleNamespace())
                .condition(rule.getCondition())
                .action(rule.getAction())
                .description(rule.getDescription())
                .priority(Integer.valueOf(rule.getPriority()))
                .id(UUID.randomUUID().toString())
                .build();
    }
}
