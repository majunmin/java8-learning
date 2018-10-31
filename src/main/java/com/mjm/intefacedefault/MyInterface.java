package com.mjm.intefacedefault;

/**
 * @author majun
 * @date 2018/10/31 7:24
 */
public interface MyInterface {

    default void getName(){
        System.out.println("MyInterface");
    }

    static void sayHello(){
        System.out.println("hello");
    }
}
