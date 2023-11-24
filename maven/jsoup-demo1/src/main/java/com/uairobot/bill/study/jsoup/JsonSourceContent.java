package com.uairobot.bill.study.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsonSourceContent {
  public static void main(String[] args) {
    Document document = null;
    try {
      document = Jsoup.connect("http://www.baidu.com").get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String text = document.text();
    String outerHtml = document.outerHtml();
    System.out.println("text");
    System.out.println(text);
    System.out.println("outerHtml");
    System.out.println(outerHtml);
  }
}
