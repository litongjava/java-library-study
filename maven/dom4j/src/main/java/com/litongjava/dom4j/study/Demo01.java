package com.litongjava.dom4j.study;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Demo01 {
  public static void main(String[] args) {
    SAXReader reader = new SAXReader();
    File file = new File("1.xml");
    try {
      Document read = reader.read(file);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }
}
