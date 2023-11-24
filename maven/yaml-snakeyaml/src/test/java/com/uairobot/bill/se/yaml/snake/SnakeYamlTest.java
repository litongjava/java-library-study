package com.uairobot.bill.se.yaml.snake;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSONObject;

public class SnakeYamlTest {

  @Test
  public void test() {
    // 初始化Yaml解析器
    Yaml yaml = new Yaml();
    // 文件输入流
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.yml");
    // 读入文件
    Object result = yaml.load((inputStream));
    // 读入完成后关闭流
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println(result.getClass());
    System.out.println(result);
  }

  @Test
  public void loadAsLinkedHashMap() {
    // 初始化Yaml解析器
    Yaml yaml = new Yaml();
    // 文件输入流
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.yml");
    // 读入文件
    LinkedHashMap<?, ?> loadAs = yaml.loadAs(inputStream, LinkedHashMap.class);
    // 读入完成后关闭流
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Object object = loadAs.get("server");
    System.out.println(object.getClass());
    System.out.println(object);
    Object port = ((LinkedHashMap<?, ?>) object).get("port");
    System.out.println(port.getClass());
    System.out.println(port);
  }

  @Test
  public void loadAsJSON() {
    // 初始化Yaml解析器
    Yaml yaml = new Yaml();
    // 文件输入流
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.yml");
    // 读入文件
    JSONObject loadAs = yaml.loadAs(inputStream, JSONObject.class);
    // 读入完成后关闭流
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Object object = loadAs.get("server");
    System.out.println(object.getClass());
    System.out.println(object);
    Object port = ((LinkedHashMap<?, ?>) object).get("port");
    System.out.println(port.getClass());
    System.out.println(port);
  }
  /*
   java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to com.alibaba.fastjson.JSONObject
  at com.uairobot.bill.se.yaml.snake.SnakeYamlTest.loadAsJSON(SnakeYamlTest.java:65)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.lang.reflect.Method.invoke(Method.java:498)
  at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
  at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
  at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
  at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
  at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
  at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
  at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
  at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
  at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
  at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
  at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
  at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
  at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
  at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
  at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
   */
}
