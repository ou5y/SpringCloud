package com.azcx9d.agency.base;

import com.azcx9d.Thread.OrderClean;
import com.azcx9d.common.util.Arith;
import com.azcx9d.common.util.CalendarUtil;

public class TestCase {

	public static void main(String args[]) {
        MyThread4 t4 = new MyThread4();
        MyThread5 t5 = new MyThread5();
        Thread t1 = new Thread(t4);
        Thread t2 = new Thread(t5);
        t1.setPriority(Thread.NORM_PRIORITY + 3);// 使用setPriority()方法设置线程的优先级别，这里把t1线程的优先级别进行设置
        /*
11          * 把线程t1的优先级(priority)在正常优先级(NORM_PRIORITY)的基础上再提高3级
12          * 这样t1的执行一次的时间就会比t2的多很多 　　　　
13          * 默认情况下NORM_PRIORITY的值为5
14          */
        t1.start();
        t2.start();
        System.out.println("t1线程的优先级是：" + t1.getPriority());
        // 使用getPriority()方法取得线程的优先级别，打印出t1的优先级别为8
    }
}

class MyThread4 implements Runnable {
     public void run() {
         for (int i = 0; i <= 1000; i++) {
            System.out.println("T1：" + i);
         }
}
}

class MyThread5 implements Runnable {
     public void run() {
         for (int i = 0; i <= 1000; i++) {
             System.out.println("===============T2：" + i);
         }
     }
 }