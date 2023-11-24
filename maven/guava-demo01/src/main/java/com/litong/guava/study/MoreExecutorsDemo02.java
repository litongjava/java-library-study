package com.litong.guava.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MoreExecutorsDemo02 {
  public static void main(String[] args) {
    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
    threadFactoryBuilder.setNameFormat("async-pool-%d");
    ThreadFactory threadFactory = threadFactoryBuilder.build();

    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3000);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, workQueue, threadFactory);

    ExecutorService executorService = MoreExecutors.getExitingExecutorService(threadPoolExecutor);
    executorService.submit(() -> {
      try {
        Thread.sleep(110000);
        System.out.println(Thread.currentThread().getName() + "@666");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println(Thread.currentThread().getName() + "@888");
  }
}
