package com.litongjava.tesseract.demo;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCRDemoTest {

  /**
   * 测试java获取环境遍历
   */
  @Test
  public void testGetEnv() {
    String getenv = System.getenv("TESSDATA_PREFIX");
    log.info(getenv);
  }
  
  
  /**
   *  测试Path路径
   */
  @Test
  public void getPath() {
    String envPath = System.getenv("PATH");
    String[] split = envPath.split(";");
    for (String string : split) {
      System.out.println(string);
    }
  }
  
  @Test
  public void compare() {
  }

}
