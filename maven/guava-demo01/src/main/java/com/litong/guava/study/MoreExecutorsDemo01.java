package com.litong.guava.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MoreExecutorsDemo01 {
  public static void main(String[] args) {
    // 实例化threadFactoryBuilder
    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
    // 设置threadFactoryBuilder参数
    threadFactoryBuilder.setDaemon(true).setNameFormat("async-pool-%d");
    // 建立threadFactory
    ThreadFactory threadFactory = threadFactoryBuilder.build();
    // 实例化executorService
    ExecutorService executorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(3000),
        threadFactory);
    // 使用线程池执行1个线程
    executorService.submit(() -> {
      try {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "@666");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    // 输出线程名称
    System.out.println(Thread.currentThread().getName() + "@888");
    // 给线程池增加1个关闭钩子
    MoreExecutors.addDelayedShutdownHook(executorService, 3000, TimeUnit.MILLISECONDS);
  }
}