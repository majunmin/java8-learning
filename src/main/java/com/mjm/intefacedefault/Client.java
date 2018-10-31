package com.mjm.intefacedefault;

/**
 * @author majun
 * @date 2018/10/31 7:28
 *
 * 1.当继承的父类和实现的接口中有相同签名的方法时，优先使用父类的方法。
 *
 * 2.当接口的父接口中也有同样的默认方法时，就近原则调用子接口的方法。
 *
 * 3.当实现的多个接口中有相同签名的方法时，必须在实现类中通过重写方法解决冲突问题，否者无法通过编译，在重写的方法中可以通过 接口名.super.方法名(); 的方式显示调用需要的方法。
 */
public class Client {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.getName();

        MyInterface.sayHello();
    }
}
