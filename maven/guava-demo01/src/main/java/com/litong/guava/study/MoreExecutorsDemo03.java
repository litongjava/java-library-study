package com.litong.guava.study;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MoreExecutorsDemo03 {
  public static void main(String[] args) {
    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
    threadFactoryBuilder.setNameFormat("async-pool-%d");
    ThreadFactory threadFactory = threadFactoryBuilder.build();
    
    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(3000);
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MINUTES, workQueue,
        threadFactory);

    ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(threadPoolExecutor);
    //使用新线程执行任务,执行成功返回1 最后1个参数的值
    ListenableFuture future = listeningExecutorService.submit(() -> {
      try {
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + "@666");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, 1);
    
    //添加回调,处理线程执行成功和执行失败
    Futures.addCallback(future, new FutureCallback() {

      @Override
      public void onSuccess(Object result) {
        System.out.println("success");
        System.out.println(Thread.currentThread().getName() + "@" + result);
      }

      @Override
      public void onFailure(Throwable t) {
        System.out.println("fail");
        System.out.println(Thread.currentThread().getName() + "@" + t.getMessage());
      }
    }, threadPoolExecutor);
    
    System.out.println(Thread.currentThread().getName() + "@888");
  }
}
