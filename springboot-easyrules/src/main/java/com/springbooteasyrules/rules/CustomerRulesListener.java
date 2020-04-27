package com.springbooteasyrules.rules;

import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;

import java.lang.reflect.*;
import java.util.*;

public class CustomerRulesListener implements RuleListener {

    private Map<Rule, ? super RulesException> failureMessageMap;
    private boolean hasError;

    public CustomerRulesListener() {
        failureMessageMap = new HashMap<>();
        hasError = false;
    }

    public Map<Rule, ? super RulesException> getFailureMessageMap() {
        return failureMessageMap;
    }

    public void setFailureMessageMap(Map<Rule, ? super RulesException> failureMessageMap) {
        this.failureMessageMap = failureMessageMap;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    @Override
    public boolean beforeEvaluate(Rule rule) {
        return true;
    }

    @Override
    public void beforeExecute(Rule rule) {
    }

    @Override
    public void onSuccess(Rule rule) {
    }

    @Override
    public void onFailure(Rule rule, Exception exception) {
        Throwable throwable = ((InvocationTargetException) exception).getTargetException();
        RulesException targetException = new RulesException(throwable.getMessage(), throwable);
        failureMessageMap.put(rule, targetException);
        hasError = true;
    }
}
