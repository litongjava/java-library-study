package com.uairobot.bill.study.jsoup;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class JsonLogin {
  public static void main(String[] args) {
    String url = "http://www.iotcloud168.com:8083/user/login?username=181228001&password=123";
    Connection connect = Jsoup.connect(url);
    Response execute = null;
    try {
      execute = connect.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Map<String, String> cookies = execute.cookies();
    System.out.println(cookies);
  }
}
