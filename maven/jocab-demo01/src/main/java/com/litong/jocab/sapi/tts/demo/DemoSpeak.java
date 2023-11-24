package com.litong.jocab.sapi.tts.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.litong.utils.dll.LibraryUtil;

public class DemoSpeak {
  public static void main(String[] args) throws Exception {
    LibraryUtil.addLibary();
    String s = "见是个好人";
    // String s = "The SpVoice object brings the text-to-speech (TTS) engine
    // capabilities to applications using SAPI automation. An application can create
    // numerous SpVoice objects, each independent of and capable of interacting with
    // the others. An SpVoice object, usually referred to simply as a voice, is
    // created with default property settings so that it is ready to speak
    // immediately.";
    speakString(s);
    speakString("通也是好人");

//    String path = "text\\过秦论.txt";
//    speakText(path);
  }

  private static void speakString(String s) {
    // 创建与微软应用程序的新连接。传入的参数是注册表中注册的程序的名称。
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    // 获取执行对象
    Dispatch spVoice = ax.getObject();
    // 音量 0-100
    ax.setProperty("Volume", new Variant(100));
    // 语音朗读速度 -10 到 +10
    ax.setProperty("Rate", new Variant(-2));
    try {
      // 执行朗读
      Dispatch.call(spVoice, "Speak", new Variant(s));
      // 关闭执行对象
      spVoice.safeRelease();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 关闭应用程序连接
      ax.safeRelease();
    }
  }

  public static void speakText(String path) throws Exception {
    ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
    // 音量 0-100
    sap.setProperty("Volume", new Variant(100));
    // 语音朗读速度 -10 到 +10
    sap.setProperty("Rate", new Variant(-1));
    // 获取执行对象
    Dispatch sapo = sap.getObject();

    // 输入文件
    File srcFile = new File(path);
    // 使用包装字符流读取文件
    BufferedReader br = new BufferedReader(new FileReader(srcFile));
    String content = br.readLine();
    try {
      // 执行朗读
      while (content != null) {
        Dispatch.call(sapo, "Speak", new Variant(content));
        content = br.readLine();
      }
      // 关闭执行对象
      sapo.safeRelease();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      br.close();
      // 关闭应用程序连接
      sap.safeRelease();
    }
  }
}
