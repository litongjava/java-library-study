package com.litongjava.ws.schild;

import org.junit.Test;

public class VideoToAudioUtilsTest {

  @Test
  public void test() {
    // 转为pcm
    // -acodec pcm_s16le -f s16le -ac 1 -ar 16000 -i 8k.pcm
    String srcFilePath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装.mp3";
    String dstFilePath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装.pcm";
    //VideoToAudioUtils.transform2Pcm(srcFilePath, dstFilePath);
  }

}
