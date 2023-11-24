package com.bill.word.server;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;

public class POIDocumentUtil {

  public static HWPFDocument newHWPFDocument(InputStream inputStream) {

    HWPFDocument wordDocument = null;
    try {
      wordDocument = new HWPFDocument(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return wordDocument;
  }

}
