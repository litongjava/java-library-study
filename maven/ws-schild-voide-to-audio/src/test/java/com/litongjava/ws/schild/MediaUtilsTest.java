package com.litongjava.ws.schild;

import java.io.File;
import java.util.Map;

import org.junit.Test;

import com.litongjava.utils.ffmpeg.MediaUtils;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class MediaUtilsTest {

  @Test
  public void testGetInfo() throws InputFormatException, EncoderException {
    //String inputPath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装.mp3";
    String inputPath="E:\\dev_workspace\\java\\hg_project\\bill-asr-server-agent\\upload\\1580976429.pcm";
    File input = new File(inputPath);
    Map<String, String> info = MediaUtils.getMediaInfoToMap(inputPath);
    for (Map.Entry<String, String> e : info.entrySet()) {
      System.out.println(e.getKey() + "=" + e.getValue());
    }
  }
}
