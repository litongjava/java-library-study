package com.litong.jocab.sapi.tts.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.litong.jocab.sapi.tts.SapiSpVoiceUtils;

import com.litong.utils.digest.MD5Util;
import com.litong.utils.dll.LibraryUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
public class DemoSpeak2 {

  
  public static void main(String[] args) throws IOException {
    String libPath="lib";
    String absLibPath = new File(libPath).getAbsolutePath();
    System.out.println(absLibPath);
    LibraryUtil.addLibDir(absLibPath);

    //LibraryUtil.addLibary();
    stringToFile("今天猪肉又涨价了,一下子涨了两块钱");
    stringToFile("今天猪y羊肉又涨价了");
    // String path = "text\\过秦论.txt";
    // textToFile(path);
  }

  /**
   * 语音转文字音频
   * 
   * @param s
   */
  public static void stringToFile(String s) {
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    try {
      // 运行时输出语音内容
      Dispatch spVoice = ax.getObject();
      String filePath = getFilePath(ax, s,6,100,-2);

      // 下面是构建文件流到成语音文件
      ax = new ActiveXComponent("Sapi.SpFileStream");
      Dispatch spFileStream = ax.getObject();

      ax = new ActiveXComponent("Sapi.SpAudioFormat");
      Dispatch spAudioFormat = ax.getObject();

      // 设置音频流格式
      Dispatch.put(spAudioFormat, "Type", new Variant(22));
      // 设置文件输出流格式
      Dispatch.putRef(spFileStream, "Format", spAudioFormat);
      // 调用输出 文件流打开方法，创建一个.wav文件
      Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new Variant(3), new Variant(true));
      // 设置声音对象的音频输出流为输出文件对象
      Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
      // 设置音量 0到100
      Dispatch.put(spVoice, "Volume", new Variant(100));
      // 设置朗读速度
      Dispatch.put(spVoice, "Rate", new Variant(-2));
      // 开始朗读,实际上是合成得到文件
      Dispatch.call(spVoice, "Speak", new Variant(s));

      // 关闭输出文件
      Dispatch.call(spFileStream, "Close");
      Dispatch.putRef(spVoice, "AudioOutputStream", null);

      spAudioFormat.safeRelease();
      spFileStream.safeRelease();
      spVoice.safeRelease();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ax.safeRelease();
    }
  }

  private static String getFilePath(ActiveXComponent ax, String text,int formatType,int volume,int rate) {
    File file = new File("tts");
    if (!file.exists()) {
      file.mkdirs();
      log.info("mkdir:{}", file.getAbsoluteFile());
    }
    // 文件名规则
    // voice_type_volume_rate_md5
    String voice = SapiSpVoiceUtils.getCurrentVoice(ax);
    String fileName = "tts/" + voice + "_" + formatType + "_" + volume + "_" + rate + "_" + MD5Util.encode(text);
    if (formatType == 6) {
      fileName += ".wav";
    }
    return fileName;
  }

  public static void textToFile(String path) {
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    try {
      // 运行时输出语音内容
      Dispatch spVoice = ax.getObject();

      // 下面是构建文件流到成语音文件
      ax = new ActiveXComponent("Sapi.SpFileStream");
      Dispatch spFileStream = ax.getObject();

      ax = new ActiveXComponent("Sapi.SpAudioFormat");
      Dispatch spAudioFormat = ax.getObject();

      // 设置音频流格式
      Dispatch.put(spAudioFormat, "Type", new Variant(22));
      // 设置文件输出流格式
      Dispatch.putRef(spFileStream, "Format", spAudioFormat);
      // 调用输出 文件流打开方法，创建一个.wav文件
      Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new Variant(3), new Variant(true));
      // 设置声音对象的音频输出流为输出文件对象
      Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
      // 设置音量 0到100
      Dispatch.put(spVoice, "Volume", new Variant(100));
      // 设置朗读速度
      Dispatch.put(spVoice, "Rate", new Variant(-2));
      // 输入文件
      File srcFile = new File(path);
      // 使用包装字符流读取文件
      BufferedReader br = new BufferedReader(new FileReader(srcFile));
      String content = br.readLine();
      // 开始朗读,实际上是合成得到文件
      while (content != null) {
        Dispatch.call(spVoice, "Speak", new Variant(content));
        content = br.readLine();
      }

      // 关闭输出文件
      Dispatch.call(spFileStream, "Close");
      Dispatch.putRef(spVoice, "AudioOutputStream", null);

      spAudioFormat.safeRelease();
      spFileStream.safeRelease();
      spVoice.safeRelease();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ax.safeRelease();
    }
  }
}