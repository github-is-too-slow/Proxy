package com.billion.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author Billion
 * @create 2021/02/23 14:51
 */
public class DynamicProxy {
    public static void main(String[] args) {
        Animal person = new Person();
        Animal proxyPerson = (Animal) ProxyFactory.getProxyInstance(person);
        proxyPerson.eat();
        proxyPerson.sleep();
    }
}

/*
 动态代理需要解决的两个问题：
    1.根据被代理对象动态生成代理对象
    2.当调用代理对象的方法a时，如何动态的调用被代理对象的同名方法a
 */

class ProxyFactory {
    /**
     * 解决问题1：根据被代理对象动态生成代理对象
     * @param obj：被代理对象
     * @return 代理对象
     */
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public void bind(Object obj){
        this.obj = obj;
    }

    /**
     * 解决问题2：当调用代理对象的方法a时，如何动态的调用被代理对象的同名方法a
     *           当调用代理对象的方法a时，会自动转到调用invoke方法
     * @param proxy 代理对象
     * @param method 调用的同名方法
     * @param args 调用的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象做一些准备工作");
        Object resultValue = method.invoke(obj, args);
        System.out.println("代理对象做一些收尾工作");
        return resultValue;
    }
}

//代理类和被代理类共同实现的接口
interface Animal{
    void eat();
    void sleep();
}

//被代理对象
class Person implements Animal{

    @Override
    public void eat() {
        System.out.println("我一天吃三顿饭");
    }

    @Override
    public void sleep() {
        System.out.println("我一天睡八个小时");
    }
}
