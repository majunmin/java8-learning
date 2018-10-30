package com.mjm.optional;

import com.mjm.apple.filter.Apple;

import java.util.Optional;

/**
 * @author majun
 * @date 2018/10/30 23:38
 *
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

    public static void main(String[] args) {

        /**
         * 传入 null 会  java.lang.NullPointerException
         * 快速锁定 异常位置
         */
//        Optional<Apple> apple = Optional.of(null); // java.lang.NullPointerException
//        System.out.println(apple.get());

//        Optional<Apple> empty = Optional.empty();
//        System.out.println(empty.get()); // java.util.NoSuchElementException: No value present

        /**
         * ofNullable
         * if null  return Optional.empty()
         * else     return Optional.of(Object)
         */
        Optional<Apple> opApple = Optional.ofNullable(null);
        System.out.println(opApple.get());
    }
}
