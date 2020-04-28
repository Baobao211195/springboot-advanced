package com.springbooteasyrules.rules;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.springbooteasyrules.processor.CustomerProcessor;

@Component
@RequestScope
public class CustomerRulesEngine extends ICustomerRulesEngine {

    // data is passed rules
    private CustomerProcessor customerProcessor;
    // for inject rules
    @Autowired
    private CustomerFirstNameRules customerFirstNameRules;

    @Override
    public void fireRules() throws RulesException {
        this.setCartRulesListener(new CustomerRulesListener());

        // set up a rules engine object
        RulesEngine rulesEngine =  RulesEngineBuilder.aNewRulesEngine()
                            .withSilentMode(isSilentMode)
                            .withRuleListener(customerRulesListener)
                            .withSkipOnFirstFailedRule(true)
                            .build();

        // register the first rules
        customerFirstNameRules.setFirstName(customerProcessor.getFirstNameForRule());
        rulesEngine.registerRule(customerFirstNameRules);

        // register the second rules


        // fire rules
        rulesEngine.fireRules();
        handleException();
    }

    public CustomerProcessor getCustomerProcessor() {
        return customerProcessor;
    }

    @Override
    public void setCustomerProcessor(CustomerProcessor customerProcessor) {
        this.customerProcessor = customerProcessor;
    }

    public CustomerFirstNameRules getCustomerFirstNameRules() {
        return customerFirstNameRules;
    }

    public void setCustomerFirstNameRules(CustomerFirstNameRules customerFirstNameRules) {
        this.customerFirstNameRules = customerFirstNameRules;
    }
}
