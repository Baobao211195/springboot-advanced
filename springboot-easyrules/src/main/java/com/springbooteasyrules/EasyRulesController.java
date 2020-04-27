package com.springbooteasyrules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbooteasyrules.domain.Customer;
import com.springbooteasyrules.dto.CustomerDto;
import com.springbooteasyrules.processor.CustomerProcessor;
import com.springbooteasyrules.rules.CustomerRulesEngine;
import com.springbooteasyrules.rules.RulesException;

@RestController
public class EasyRulesController {

    @Autowired
    private CustomerRulesEngine customerRulesEngine;

    @GetMapping(value = "/run-rules")
    public Customer runRulesDemo(@RequestBody CustomerDto dto) throws RulesException {

        // set data for rules
        CustomerProcessor customerProcessor = new CustomerProcessor();
        customerProcessor.setFirstNameForRule("oanhpv to check rules");

        // trigger rules
        runSubmitOrderRulesEngine(customerProcessor);

        return  null;
    }


    /**
     * Set data for rules
     * @param customerProcessor
     * @throws RulesException
     */
    private void runSubmitOrderRulesEngine(CustomerProcessor customerProcessor) throws RulesException {
        customerRulesEngine.setCustomerProcessor(customerProcessor);
        customerRulesEngine.fireRules();
    }
}
