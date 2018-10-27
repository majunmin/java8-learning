package com.mjm.lambda.function;

/**
 * @author majun
 * @date 2018/10/25 7:32
 */
@FunctionalInterface
public interface MyFunction2<T, R> {

    R getValue(T t1, T t2);
}
