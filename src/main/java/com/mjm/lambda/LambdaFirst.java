package com.mjm.lambda;

import com.mjm.lambda.function.MyFunction1;
import com.mjm.lambda.function.MyFunction2;

/**
 * @author majun
 * @date 2018/10/25 7:29
 */
public class LambdaFirst {

    public static void main(String[] args) {
        System.out.println(handlerStr("\t\t\tkawayi", str -> str.trim()));
        System.out.println(handlerStr("\t\t\tkawayi", str -> str.toUpperCase()));
        System.out.println(handlerStr("\t\t\tkawayi", str -> str.substring(2,4)));

        System.out.println(op(15, 14, (Integer p1, Integer p2)-> p1+p2));
        System.out.println(op(15L, 14L, (Long p1, Long p2)-> p1-p2));
        System.out.println(op("13L", "14L", (String p1, String p2)-> p1+p2));
    }

    public static String handlerStr(String str, MyFunction1 func1){
        return func1.getValue(str);
    }

    public static <T,R> R op(T p1, T p2,  MyFunction2<T,R> func2){
        return func2.getValue(p1, p2);
    }
}
