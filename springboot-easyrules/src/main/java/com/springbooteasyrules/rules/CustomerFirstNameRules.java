package com.springbooteasyrules.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;

@SpringRule
public class CustomerFirstNameRules extends  BaseCustomerRule {
    private static final Logger logger = LogManager.getLogger(CustomerFirstNameRules.class);


    //
    @Priority
    public int getPriority() {
        return Integer.MIN_VALUE;
    }

    @Condition
    public boolean when() {
        // when first name os customer is upper case
        return isUpperCase();
    }

    @Action(order = 0)
    public void checkAnyBusinessForRules() throws RulesException {
        this.setExecuted(true);
        // ========================= do stuff some business =========//

    }

}
