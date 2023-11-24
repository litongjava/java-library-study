package com.bill.word.server;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.w3c.dom.Document;

public class WordUtil {

  public static SimplePicturesManager spm;
  static {
    spm = new SimplePicturesManager();
  }

  public static Document convert(HWPFDocument wordDocument) {
    Document document = DocumentUtil.getDocument();
    WordToHtmlConverter convert = new WordToHtmlConverter(document);
    convert.setPicturesManager(spm);
    convert.processDocument(wordDocument);
    Document htmlDocument = convert.getDocument();
    return htmlDocument;
  }
}
