package com.uairobot.bill.study.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsonDocumentText {
  public static void main(String[] args) {
    Document document = null;
    try {
      document = Jsoup.connect("http://111.204.203.220:2200/webinfo/main/index.html?wd=ariF/cAOSS4=").get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String text = document.text();
    String outerHtml = document.outerHtml();
    String html = document.html();
    System.out.println(text);
    System.out.println(outerHtml);
  }

}
