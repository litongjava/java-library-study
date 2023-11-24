package com.litongjava.ws.schild.utils;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;

public class BaiduAsrUtils {
  // 设置APPID/AK/SK
  public static final String APP_ID = "25249992";
  public static final String API_KEY = "K8UZKw95idYGlwAYYLneAqsg";
  public static final String SECRET_KEY = "1POObOPyv1053fvSv6HqgGYIjDpvHMcO";

  // 初始化一个AipSpeech
  private static AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

  /**
   * 调用百度语音识别接口
   * 
   * @param path
   * @param format
   * @param rate
   * @param options
   * @return
   */
  public static JSONObject asr(String path, String format, int rate, HashMap<String, Object> options) {
    // "test.pcm", "pcm", 16000, null
    JSONObject res = client.asr(path, format, rate, options);
    // System.out.println(res.toString(2));
    return res;
  }

  public static void main(String[] args) {

    // 可选：设置网络连接参数
    client.setConnectionTimeoutInMillis(2000);
    client.setSocketTimeoutInMillis(60000);

    // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
    // client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
    // client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

    // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
    // 也可以直接通过jvm启动参数设置此环境变量
    // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

  }
}