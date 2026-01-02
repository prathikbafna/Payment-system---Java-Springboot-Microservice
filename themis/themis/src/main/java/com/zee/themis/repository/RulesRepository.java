package com.zee.themis.repository;

import com.zee.themis.entity.RuleEntity;
import com.zee.themis.entity.RuleNamespace;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends MongoRepository<RuleEntity, String> {
    List<RuleEntity> findByRuleNamespace(RuleNamespace ruleNamespace);
    List<RuleEntity> findAll();
}
