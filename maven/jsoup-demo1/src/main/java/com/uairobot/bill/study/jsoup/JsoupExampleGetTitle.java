package com.uairobot.bill.study.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupExampleGetTitle {
  public static void main(String[] args) {
    Document doc = null;
    try {
      doc = Jsoup.connect("http://www.baidu.com/").get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String title = doc.title();
    System.out.println(title);
  }
}
