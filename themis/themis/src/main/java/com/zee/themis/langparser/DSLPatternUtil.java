package com.zee.themis.langparser;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class DSLPatternUtil {
    private static final Pattern DSL_PATTERN = Pattern.compile("\\$\\((\\w+)(\\.\\w+)\\)"); //$(rulenamespace.keyword)
    private static final String DOT = ".";

    /**
     *
     * @param expression
     * @return List of dsl keywords to be parsed
     * extract keywords in format $(resolverKeyword.value)
     */
    public List<String> getListOfDslKeywords(String expression) {
        Matcher matcher = DSL_PATTERN.matcher(expression);
        List<String> listOfDslKeyword = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            listOfDslKeyword.add(group);
        }
        return listOfDslKeyword;
    }

    /**
     *
     * @param keyword
     * @return the keyword to be parsed in format (resolverKeyword.value)
     */
    public String extractKeyword(String keyword) {
        return keyword.substring(keyword.indexOf('(') + 1,
                keyword.indexOf(')'));
    }

    /**
     *
     * @param dslKeyword
     * @return keyword resolver ex: (payment.amount) -> payment
     */
    public String getKeywordResolver(String dslKeyword){
        ArrayList<String> splittedKeyword = Lists.newArrayList(Splitter.on(DOT).omitEmptyStrings().split(dslKeyword));
        return splittedKeyword.get(0);
    }

    /**
     *
     * @param dslKeyword
     * @return keyword value ex: (payment.amount) -> amount
     */
    public String getKeywordValue(String dslKeyword){
        ArrayList<String> splittedKeyword = Lists.newArrayList(Splitter.on(DOT).omitEmptyStrings().split(dslKeyword));
        return splittedKeyword.get(1);
    }
}
