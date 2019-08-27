package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:27
 * description: 非终结符表达式(Nonterminal Expression)
 */
public class Not extends Expression {
    private Expression exp;
    public Not(Expression exp) {
        this.exp = exp;
    }
    @Override
    public boolean interpret(Context ctx) {
        return !exp.interpret(ctx);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Not) {
            return exp.equals(((Not)obj).exp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(NOT " + exp.toString() + ")";
    }
}