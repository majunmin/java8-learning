package com.mjm.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author majun
 * @date 2018/10/25 7:55
 * <p>
 * Java8 内置的四大核心函数式接口
 * <p>
 * Consumer<T> : 消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T> : 供给型接口
 * T get();
 * <p>
 * Function<T, R> : 函数型接口
 * R apply(T t);
 * <p>
 * Predicate<T> : 断言型接口
 * boolean test(T t);
 *
 * Lambda
 *    的基本语法是
 * (parameters) -> expression
 *    或（请注意语句的花括号）
 * (parameters) -> { statements; }
 */
public class LambdaSecond {


    public static void main(String[] args) {

        consume(100, integer -> System.out.println("consume " + integer + "RMB"));

        List<Integer> res = getNumList(10, () -> (int) (Math.random() * 100));

        String res2 = handlerStr("mjmjmjmjmjkla", str -> str.substring(1, 4));

        List<String> list = Arrays.asList("apple", "orange", "piple", "banana", "qwert", "yuiop");

        List<String> list1 = filterStr(list, str -> str.length() > 5);
        List<String> list2 = filterStr(list, a -> a.startsWith("a"));

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    private static void consume(Integer i, Consumer<Integer> consumer) {
        consumer.accept(i);
    }

    /**
     * 产生指定个数的整数 返回集合
     *
     * @param supplier
     * @return
     */
    private static List<Integer> getNumList(int count, Supplier<Integer> supplier) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(supplier.get());
        }
        return result;
    }

    /**
     * 处理字符串  函数型接口
     * @param str
     * @param func
     * @return
     */
    private static String handlerStr(String str, Function<String, String> func) {
        return func.apply(str);
    }

    /**
     * 断言型接口  筛选字符串
     * @param origin
     * @param p
     * @return
     */
    private static List<String> filterStr(List<String> origin, Predicate<String> p){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < origin.size(); i++) {
             if (p.test(origin.get(i))){
                 result.add(origin.get(i));
             }
        }
        return result;
    }
}
