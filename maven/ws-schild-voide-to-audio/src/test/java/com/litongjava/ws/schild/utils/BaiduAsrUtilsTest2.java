package com.litongjava.ws.schild.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.litongjava.utils.ffmpeg.AudioUtils;
import com.litongjava.utils.ffmpeg.MediaUtils;

import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

public class BaiduAsrUtilsTest2 {

  String sourcePath = "H:\\video\\软件开发学习视频\\微信小程序\\第0章\\1.2微信小程序和其他编程有什么区别.mp3";

  @Test
  public void testAsr() throws InputFormatException, EncoderException {
    List<String> pcmFileList = new ArrayList<>();
    String[] segmentLengthLimit = AudioUtils.segmentLengthLimit(sourcePath, 60);
    for (String string : segmentLengthLimit) {
      String dstFilePath = string.replace(".mp3", ".pcm");
      MediaUtils.transform2Pcm(string, dstFilePath);
      pcmFileList.add(dstFilePath);
    }

    for (String string : pcmFileList) {
      System.out.println(string);
      JSONObject asr = BaiduAsrUtils.asr(string, "pcm", 16000, null);
      System.out.println(asr);
    }

  }
}
