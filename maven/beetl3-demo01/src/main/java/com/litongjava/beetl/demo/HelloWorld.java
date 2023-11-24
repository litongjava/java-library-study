package com.litongjava.beetl.demo;

public class HelloWorld {
  public static void main(String[] args) {
    String x = "Hello world";
    System.out.println(x);
    // get java version
    String key = "java.version";
    String javaVersion = System.getProperty(key);
    System.out.println(javaVersion);
  }
}