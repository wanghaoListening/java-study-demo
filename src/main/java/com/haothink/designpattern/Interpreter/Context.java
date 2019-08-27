package com.haothink.designpattern.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年08月27日 20:22
 * description:
 */
public class Context {

    private Map<Variable, Boolean> map = new HashMap<>();

    public void assign(Variable var, boolean value) {
        map.put(var, value);
    }

    public boolean lookup(Variable var) throws IllegalArgumentException {
        Boolean value = map.get(var);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }
}
