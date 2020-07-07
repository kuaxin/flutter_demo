package com.youjiaoyule.mvvmactual;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author RenGX on 2020/6/29
 */
class TestUnit {

    @Test
    public void main(){
        MaotaiJiu maotaijiu = new MaotaiJiu();


        InvocationHandler jingxiao1 = new GuitaiA(maotaijiu);


        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(maotaijiu.getClass().getClassLoader(),
                maotaijiu.getClass().getInterfaces(), jingxiao1);

        dynamicProxy.mainJiu();




    }

}


interface SellWine {
    void mainJiu();
}

class MaotaiJiu implements SellWine {

    @Override
    public void mainJiu() {

        // TODO Auto-generated method stub
        System.out.println("我卖得是茅台酒。");
    }

}

class GuitaiA implements InvocationHandler {

    private Object pingpai;

    public GuitaiA(Object pingpai) {
        this.pingpai = pingpai;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("销售开始  柜台是： "+this.getClass().getSimpleName());
        method.invoke(pingpai, args);
        System.out.println("销售结束");
        return null;
    }

}


