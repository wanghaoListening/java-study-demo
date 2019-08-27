package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:26
 * description: 非终结符表达式(Nonterminal Expression)
 */
public class Or extends Expression {


    private Expression left, right;


    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public boolean interpret(Context ctx) {
        return left.interpret(ctx) || right.interpret(ctx);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Or) {
            return left.equals(((Or)obj).left) && right.equals(((Or)obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }
}