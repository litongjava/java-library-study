package com.litong.guava.study;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MoreExecutorsDemo04 {
  public static void main(String[] args) {
    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
    threadFactoryBuilder.setNameFormat("async-pool-%d");
    ThreadFactory threadFactory = threadFactoryBuilder.build();

    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3000);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, workQueue, threadFactory);

    Executor executor = MoreExecutors.newSequentialExecutor(threadPoolExecutor);
    for (int i = 0; i < 10; i++) {
      int d = i;
      executor.execute(() -> {
        try {
          Thread.sleep(d * 1000);
          System.out.println(Thread.currentThread().getName() + "@" + d + ", now= " + System.currentTimeMillis());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
