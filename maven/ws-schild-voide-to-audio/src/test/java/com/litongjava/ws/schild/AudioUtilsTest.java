package com.litongjava.ws.schild;

import java.io.File;

import org.junit.Test;

import com.litongjava.utils.ffmpeg.AudioUtils;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class AudioUtilsTest {

  // String sourcePath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装.mp3";
  //String sourcePath = "H:\\video\\软件开发学习视频\\微信小程序\\第0章\\1.1编程是什么.mp3";
  String sourcePath="H:\\video\\软件开发学习视频\\微信小程序\\第0章\\1.2微信小程序和其他编程有什么区别.mp3";

  @Test
  public void testCutByFfmpeg() {

    String sourcePath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装.pcm";
    String targetPath = "H:\\video\\软件开发学习视频\\微信小程序\\第1章\\1.1开发者工具下载安装-00-60.pcm";
    AudioUtils.cutByFfmpeg(sourcePath, targetPath, 0, 60000);
  }

  @Test
  public void getDuration() throws InputFormatException, EncoderException {
    File input = new File(sourcePath);
    long duration = AudioUtils.getDuration(input);
    System.out.println(duration);
  }

  @Test
  public void testSegmentLengthLimit() throws InputFormatException, EncoderException {
    String[] segmentLengthLimit = AudioUtils.segmentLengthLimit(sourcePath, 60);
    for (String string : segmentLengthLimit) {
      System.out.println(string);
    }
  }
}
