package com.mjm.lambda;

import com.mjm.apple.filter.Apple;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author majun
 * @date 2018/10/25 18:46
 *
 * map 可以将方法引用(lambda表达式  匿名函数)作为 值
 *
 * 1. 方法引用
 * 什么时候使用方法引用
 *     当要传递给Lambda体内的操作，已经有实现的方法了，就可以使用方法引用了
 * 使用方法引用的前提条件
 *     1.方法引用所引用的方法的参数列表必须要和函数式接口中抽象方法的参数列表相同（完全一致）
 *     2.方法引用所引用的方法的的返回值必须要和函数式接口中抽象方法的返回值相同（完全一致）
 * 方法引用一般会有三种格式
 *     1. 实例对象名::实例方法名
 *     2. 类名::静态方法名
 *     3. 类名::实例方法名  --若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： 类名::实例方法名
 *
 * 2. 构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *     类名 :: new
 *
 * 3.数组引用
 *    类型[] :: new;
 *
 *
 */
public class MethdRef {

    static Map<String, Function<Double, Apple>> map = new HashMap<>();

    static {
        map.put("yellow", Apple::new);
        map.put("orange", Apple::new);
    }

    public static Apple giveMeApple(String color, Double weight) {
        return map.get(color.toLowerCase())
                .apply(weight);
    }

    public static void main(String[] args) {
        Apple orange = giveMeApple("orange", 45.6);
        System.out.println(orange);
    }
}
