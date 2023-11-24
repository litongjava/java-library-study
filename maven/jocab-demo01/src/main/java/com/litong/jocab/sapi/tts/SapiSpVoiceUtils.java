package com.litong.jocab.sapi.tts;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class SapiSpVoiceUtils {
  public static String getCurrentVoice(ActiveXComponent ax) {
    // //
    // 创建与微软应用程序的新连接。传入的参数是注册表中注册的程序的名称。相当实例化Sapi.SpVoice对象,但是ax并不是Sapi.SpVoice,ax是包装信息,ax包含Sapi.SpVoice
//    ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
//     获取Sapi.SpVoice对象的voice属性值,voice的属性值是一个voice对象,对象使用Dispatch包装
    Dispatch voiceDispatche = ax.getProperty("voice").toDispatch();
    // 执行voice对象的GetDescription方法,返回值可能使用String,使用Variant包装
    Variant descripton = Dispatch.call(voiceDispatche, "GetDescription");
    ax.safeRelease();
    return descripton.toString();
  }
}
