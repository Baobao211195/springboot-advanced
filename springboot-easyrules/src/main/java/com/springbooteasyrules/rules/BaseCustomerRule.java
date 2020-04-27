package com.springbooteasyrules.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomerRule {
    private String firstName;
    private boolean isUpperCase;
    private boolean isExecuted;
}
