package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:23
 * description: 终结符表达式(Terminal Expression)
 */
public class Constant extends Expression {

    private boolean value;
    public Constant(boolean value) {
        this.value = value;
    }
    @Override
    public boolean interpret(Context ctx) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Constant) {
            return this.value == ((Constant)obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return value+"";
    }
}
