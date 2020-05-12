package com.jvm.gc;
import	java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import	java.util.Date;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName Problem01
 * @Description 模拟产生GC的代码
 * @Author liangxp
 * @Date 2020/5/11 17:27
 **/
public class Problem01 {

    private static class CardInfo{
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthday = new Date();
        public void m(){
        }
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {

        executor.setMaximumPoolSize(50);

        for (;;){
            modelFit();

            Thread.sleep(100);
        }


    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info ->{
            executor.scheduleAtFixedRate(() -> {
                info.m();
            },2,3,TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }
        return taskList;
    }
}
