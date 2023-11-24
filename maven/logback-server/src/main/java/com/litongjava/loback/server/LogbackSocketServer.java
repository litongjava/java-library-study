package com.litongjava.loback.server;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.SimpleSocketServer;

public class LogbackSocketServer {
  public static void main(String[] args) {
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    SimpleSocketServer sss = new SimpleSocketServer(lc, 9999);
    sss.start();
  }
}