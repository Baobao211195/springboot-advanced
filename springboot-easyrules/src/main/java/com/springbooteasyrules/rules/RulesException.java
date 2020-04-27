package com.springbooteasyrules.rules;

import java.util.*;

public class RulesException extends  Exception {
    public RulesException(String message) {
        super(message);
    }

    public RulesException(String message, Throwable cause) {
        super(message, cause);
    }
}
