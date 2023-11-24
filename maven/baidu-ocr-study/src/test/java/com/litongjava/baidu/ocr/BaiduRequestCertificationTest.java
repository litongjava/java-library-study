package com.litongjava.baidu.ocr;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaiduRequestCertificationTest {

  @Test
  public void test() {
    String clientId = BaiduRequestCertification.getClientId();
    String clientSccert = BaiduRequestCertification.getClientSccert();
    log.info("clientId:{},clientSccert:{}", clientId, clientSccert);
  }

}
