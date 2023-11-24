package com.litongjava.ws.schild.utils;

import org.json.JSONObject;
import org.junit.Test;

public class BaiduAsrUtilsTest {

  String pcmFullPath = "H:\\video\\软件开发学习视频\\微信小程序\\第0章/1.1编程是什么_chunks/0000.pcm";

  @Test
  public void testAsr() {
    JSONObject jsonObject = BaiduAsrUtils.asr(pcmFullPath, "pcm", 16000, null);
    System.out.println(jsonObject.toString());
    System.out.println(jsonObject.toString(2));
  }

}
