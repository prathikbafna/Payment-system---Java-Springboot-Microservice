package com.zee.themis.dslresolver;

public interface DSLResolver {
    String getResolverKeyword();
    Object resolveValue(String keyword);
}
