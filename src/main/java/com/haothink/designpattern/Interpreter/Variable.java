package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:24
 * description: 终结符表达式(Terminal Expression)
 */
public class Variable extends Expression {


    private String name;
    public Variable(String name) {
        this.name = name;
    }
    @Override
    public boolean interpret(Context ctx) {
        return ctx.lookup(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Variable) {
            return this.name.equals(((Variable)obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
