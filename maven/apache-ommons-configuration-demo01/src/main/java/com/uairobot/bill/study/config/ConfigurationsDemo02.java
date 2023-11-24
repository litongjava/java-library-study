package com.uairobot.bill.study.config;

public class ConfigurationsDemo02 {
  public static void main(String[] args) {
    String string = CommonsConfigUtil.get("httpResponseEncoding");
    System.out.println(string);
  }
}
