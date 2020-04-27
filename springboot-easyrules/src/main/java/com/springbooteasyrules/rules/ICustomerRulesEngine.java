package com.springbooteasyrules.rules;

import org.springframework.beans.factory.annotation.Value;

import com.springbooteasyrules.processor.CustomerProcessor;
import java.util.*;
import javax.swing.RowFilter.*;

public abstract  class ICustomerRulesEngine {

    protected CustomerProcessor customerProcessor;
    protected CustomerRulesListener customerRulesListener;

    @Value("${easyrule.silent.mode:false}")
    protected boolean isSilentMode;

    public abstract void fireRules() throws RulesException;

    public void handleException() throws RulesException {
        if(customerRulesListener.isHasError()) {
            RulesException exception = (RulesException)customerRulesListener.getFailureMessageMap().entrySet()
                                            .stream().map(Map.Entry::getValue).findFirst().orElse(null);
            if(exception != null) {
                throw  (RulesException) exception.getCause();
            }
        }
    }

    public void setCustomerProcessor(CustomerProcessor customerProcessor) {
        this.customerProcessor = customerProcessor;
    }

    public void setCartRulesListener(CustomerRulesListener customerRulesListener) {
        this.customerRulesListener = customerRulesListener;
    }

    public void setSilentMode(boolean silentMode) {
        isSilentMode = silentMode;
    }
}
