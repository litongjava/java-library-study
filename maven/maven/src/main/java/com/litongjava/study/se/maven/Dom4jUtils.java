package com.litongjava.study.se.maven;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author create by ping-e-lee on 2021年6月24日 上午12:11:28 
 * @version 1.0 
 * @desc
 */
public class Dom4jUtils {

  /**
   * 吸入xml文件
   * @param document
   * @param xmlPath
   */
  public static void write(Document document, String xmlPath) {
    XMLWriter xmlWriter = null;
    try {
      FileOutputStream out = new FileOutputStream(xmlPath);
      xmlWriter = new XMLWriter(out, OutputFormat.createPrettyPrint());
      xmlWriter.write(document);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        xmlWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
