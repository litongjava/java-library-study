package com.litongjava.logbak.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClient {
  private static final Logger logger = LoggerFactory.getLogger(MyClient.class);

  public static void main(String[] args) {
    logger.info("开始运行");
    for (int i = 0; i < 1000; i++) {
      logger.info("来自客户端消息: " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    logger.info("结束运行");
  }
}