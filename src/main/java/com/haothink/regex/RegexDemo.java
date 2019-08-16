package com.haothink.regex;

/**
 * @author wanghao
 * @date 2019年07月23日 18:15
 * description:
 */
public class RegexDemo {


    /**
     * 判断小数点后2位的数字的正则表达式
     */
    private static final String MONEY_DOT_REGEX = "(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})";


    public static void main(String[] args) {

        removeMoneyDotNum();
    }

    private static void removeMoneyDotNum(){

        String str = "大家好 这个汉堡234.00,哪个鸡腿 12.10,一瓶可乐 0.23,总共 1023.00";

        String newStr = str.replaceAll(MONEY_DOT_REGEX,"$1");
        System.out.println(newStr);
    }
}
