package com.haothink.designpattern.Interpreter;

/**
 * @author wanghao
 * @date 2019年08月27日 20:28
 * description: 非终结符表达式(Nonterminal Expression)
 */
public class Client {


    public static void main(String[] args) {

        Context context = new Context();
        Variable xVariable = new Variable("X");
        Variable yVariable = new Variable("Y");
        context.assign(xVariable, false);
        context.assign(yVariable, true);

        Constant constant = new Constant(true);

        Expression expression = new Or(new And(constant, xVariable), new And(constant, new Not(xVariable)));
        System.out.println("X = " + xVariable.interpret(context));
        System.out.println("Y = " + yVariable.interpret(context));
        System.out.println(expression.toString() + " = " + expression.interpret(context));
    }
}
