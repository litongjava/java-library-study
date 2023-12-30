package com.litongjava.tesseract.demo;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OCRDemoTest {

  /**
   * ����java��ȡ��������
   */
  @Test
  public void testGetEnv() {
    String getenv = System.getenv("TESSDATA_PREFIX");
    log.info(getenv);
  }
  
  
  /**
   *  ����Path·��
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
