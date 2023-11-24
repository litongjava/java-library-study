package com.litongjava.ws.schild;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import com.litongjava.utils.reflection.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassPathResourcesTest {

  @Test
  public void testGetInputStream() {
    // 模板地址
    String templateFileName = "template" + File.separator + "xls" + File.separator + "vipReport.xlsx";
    log.info("模板文件:{}", templateFileName);

    InputStream inputStream = new ClassPathResource(templateFileName).getInputStream();
    log.info("inputStream:{}", inputStream);
  }

  @Test
  public void getGetInputStramWithClassloader() {
    URL resource = this.getClass().getClassLoader().getResource("");
    System.out.println(resource.getFile());
    
    String templateFileName = "template" + File.separator + "xls" + File.separator + "vipReport.xlsx";
    resource = this.getClass().getClassLoader().getResource(templateFileName);
    System.out.println(resource.getFile());
  }
}
