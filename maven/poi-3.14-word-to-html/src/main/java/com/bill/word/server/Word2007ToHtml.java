package com.bill.word.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Word2007ToHtml {
  public static void main(String[] args) {
    // Word2007ToHtml word2007ToHtml = new Word2007ToHtml();
    // try {
    // word2007ToHtml.word2007ToHtml();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    String docPath = "03java-se类库.doc";
    Word2Html word2Html = new Word2Html();
    word2Html.convertFromDoc(docPath, "");
  }

  public void word2007ToHtml() throws Exception {
    String sourceFileName = "（技术部分）1-厦门锋联.docx";
    String filepath = sourceFileName.replace(".docx", "").replace(".doc", "");
    File file = new File(filepath);
    if (!file.exists())
      file.mkdirs();
    String targetFileName = filepath + File.separator + "1496717486420.html";
    String imagePathStr = filepath + "/image/";
    OutputStreamWriter outputStreamWriter = null;
    try {
      XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
      XHTMLOptions options = XHTMLOptions.create();
      // 存放图片的文件夹
      options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
      // html中图片的路径
      options.URIResolver(new BasicURIResolver("image"));
      outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
      XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
      xhtmlConverter.convert(document, outputStreamWriter, options);
    } finally {
      if (outputStreamWriter != null) {
        outputStreamWriter.close();
      }
    }
  }

}