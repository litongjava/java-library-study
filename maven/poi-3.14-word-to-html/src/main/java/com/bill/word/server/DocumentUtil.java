package com.bill.word.server;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class DocumentUtil {
  private static DocumentBuilderFactory factory;
  private static DocumentBuilder builder;
  private static Document document;
  static {
    factory = DocumentBuilderFactory.newInstance();
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    document = builder.newDocument();
  }

  public static Document newDocument() {
    Document document = builder.newDocument();
    return document;
  }

  public static Document getDocument() {
    return document;
  }
}
