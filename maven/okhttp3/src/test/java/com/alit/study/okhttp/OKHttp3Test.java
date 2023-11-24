package com.alit.study.okhttp;

import java.io.IOException;

import org.junit.Test;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttp3Test {

  String url = "http://222.76.243.153:8002/ask/ask/ask?platform=weixin-public&question=你好";

  @Test
  public void testGet() {
    // 初始化，在一个应用中建议只需要一个OkHttpClient实例
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()// 利用建造者模式创建Request对象
        .url(url)// 设置请求的URL
        .build();// 生成Request对象
    Response response = null;
    try {
      // 将请求添加到请求队列等待执行，并返回执行后的Response对象
      response = client.newCall(request).execute();
      if (response.code() == 200) {
        // response.body().string()是获取返回的结果，此句话只能调用一次，再次调用获得不到结果。
        String result = response.body().string();
        System.out.println(result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (response != null) {
        response.body().close();
      }
    }
  }

  @Test
  public void testNoSync() {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();
    // enqueue就是将此次的call请求加入异步请求队列，会开启新的线程执行，并将执行的结果通过Callback接口回调的形式返回。
    client.newCall(request).enqueue(new Callback() {

      // 请求成功回调方法
      public void onResponse(Call call, Response response) throws IOException {
        String result = response.body().string();
        System.out.println("执行成功");
        System.out.println(result);
        response.body().close();
      }

      // 请求失败回调方法
      public void onFailure(Call call, IOException e) {
        System.out.println("执行失败");
        e.printStackTrace();
      }
    });
    try {
      Thread.sleep(10000); //休眠10s等待异步线程执行完成
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
  }

}
