package com.zee.themis.langparser;

import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class MVELParser {

    /**
     *
     * @param expression
     * @param inputObjects
     * @return
     * parses mvel expression based on the resolved keywords from DSL parser ,
     * returns false if the expression is not mvel parseable
     */
    public boolean parseMvelExpression( String expression, Map<String, Object> inputObjects){
        try {
            return MVEL.evalToBoolean(expression,inputObjects);
        }catch (Exception e){
            log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
        }
        return false;
    }
}
