package com.mjm.intefacedefault;

/**
 * @author majun
 * @date 2018/10/31 7:26
 */
public interface MyFun {

    default void getName(){
        System.out.println("MyFun");
    }
}
