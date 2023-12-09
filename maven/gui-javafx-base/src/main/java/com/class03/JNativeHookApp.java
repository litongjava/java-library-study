package com.class03;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by litonglinux@qq.com on 12/8/2023_6:26 PM
 */
public class JNativeHookApp extends Application {
  @Override
  public void init() throws Exception {
    super.init();
    Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    // 设置日志级别
    // logger.setLevel(Level.OFF); // Level.OF F完全关闭日志记录
    logger.setLevel(Level.SEVERE); // Level.SEVERE只打印严重错误日志

    // 取消使用默认的日志处理器
    logger.setUseParentHandlers(false);

    GlobalScreen.setEventDispatcher(new VoidDispatchService());
    GlobalScreen.registerNativeHook();
    GlobalScreen.addNativeKeyListener(new GlobalKeyListener());

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
