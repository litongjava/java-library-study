package com.liotngjava.luaj.demo01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;

public class TestLogger {
  public static String TAG = "Logger";
  private static Log logger = LogFactory.getLog(Logger.class);;

  public TestLogger() {
    if (logger == null) {
      logger = LogFactory.getLog(Logger.class);
    }
  }

  public void testLogger(String str) {
    logger.info(str);
  }

  public static void info(String content) {
    logger.info(content);
  }
}
