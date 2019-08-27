package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:26
 * description:
 */
public class And extends Expression{

    private Expression left, right;
    public And(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public boolean interpret(Context ctx) {
        return left.interpret(ctx) && right.interpret(ctx);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof And) {
            return left.equals(((And)obj).left) && right.equals(((And)obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}
