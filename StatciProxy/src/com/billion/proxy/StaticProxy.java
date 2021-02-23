package com.billion.proxy;

/**
 * 静态代理
 * @author Billion
 * @create 2021/02/23 14:25
 */
public class StaticProxy {
    public static void main(String[] args) {
        //被代理对象
        ClothesFactory nike = new NikeClothesFactory();
        //代理对象
        ClothesFactory proxy = new ProxyClothesFactory(nike);
        proxy.productClothes();
    }
}


//代理类和被代理类共同实现的接口
interface ClothesFactory{
    void productClothes();
}

//代理类
class ProxyClothesFactory implements ClothesFactory {
    private ClothesFactory clothesFactory;

    public ProxyClothesFactory(ClothesFactory clothesFactory) {
        this.clothesFactory = clothesFactory;
    }

    @Override
    public void productClothes() {
        System.out.println("代理工厂做一些准备工作");
        clothesFactory.productClothes();
        System.out.println("代理工厂做一些收尾工作");
    }
}

//被代理类
class NikeClothesFactory implements ClothesFactory{

    @Override
    public void productClothes() {
        System.out.println("生产一千件Nike运动衣");
    }
}
