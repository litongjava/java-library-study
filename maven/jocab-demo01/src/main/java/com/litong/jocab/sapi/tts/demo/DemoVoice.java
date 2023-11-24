package com.litong.jocab.sapi.tts.demo;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.litong.utils.dll.LibraryUtil;

/**
 * @author bill robot
 * @date 2020年8月16日_上午11:31:55 
 * @version 1.0 
 * @desc
 */
public class DemoVoice {
  public static void main(String[] args) {
    LibraryUtil.addLibary();
    // getVoices();
//    getCurrentVoice();
    getVoices();
  }

  /**
   * 获取当前发音人
   */
  private static void getCurrentVoice() {
    // //
    // 创建与微软应用程序的新连接。传入的参数是注册表中注册的程序的名称。相当实例化Sapi.SpVoice对象,但是ax并不是Sapi.SpVoice,ax是包装信息,ax包含Sapi.SpVoice
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    // 获取Sapi.SpVoice对象的voice属性值,voice的属性值是一个voice对象,对象使用Dispatch包装
    Dispatch voiceDispatche = ax.getProperty("voice").toDispatch();
    // 执行voice对象的GetDescription方法,返回值可能使用String,使用Variant包装
    Variant descripton = Dispatch.call(voiceDispatche, "GetDescription");
    System.out.println(descripton);
    ax.safeRelease();
  }

  /**
   * 获取发音人数量
   */
  public static void getVoiceCount() {
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    // 运行时输出语音内容
    Dispatch spVoice = ax.getObject();
    // 获取
    Dispatch voiceItems = Dispatch.call(spVoice, "GetVoices").toDispatch();
    String string = Dispatch.call(voiceItems, "Count").toString();
    int count = Integer.valueOf(string);
    System.out.println(count);

    spVoice.safeRelease();
    ax.safeRelease();
  }

  /**
   *获取所有发音人名称
   */
  public static void getVoices() {
    // 创建与微软应用程序的新连接。传入的参数是注册表中注册的程序的名称。
    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
    // 运行时输出语音内容
    Dispatch spVoice = ax.getObject();
    // 获取所有发音人
    Dispatch voiceItems = Dispatch.call(spVoice, "GetVoices").toDispatch();
    // 获取发音人数量
    int count = Integer.valueOf(Dispatch.call(voiceItems, "Count").toString());
    String[] voices = null;
    if (count > 0) {
      // 初始化数组数量
      voices = new String[count];
      for (int i = 0; i < count; i++) {
        // 获取其中的一个item,就是发音人
        Dispatch voiceItem = Dispatch.call(voiceItems, "Item", new Variant(i)).toDispatch();
        // 获取发音人的描述
        String voice = Dispatch.call(voiceItem, "GetDescription").toString();
        // 将描述存入数组
        voices[i] = voice;
      }
    }
    for (int i = 0; i < voices.length; i++) {
      System.out.println(i + ":" + voices[i]);
    }

    spVoice.safeRelease();
    ax.safeRelease();
  }

}
