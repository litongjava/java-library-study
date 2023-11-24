package com.litong.poi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author litong
 * @date 2018年5月30日_下午9:04:13 
 * @version 1.0 
 */
public class DocWriter {
  public static void searchAndReplace(String src, String dst, Map<String, String> map) {
    XWPFDocument doc = null;
    try {
      // 将docx文件读取成opc
      OPCPackage pkg = POIXMLDocument.openPackage(src);
      // 打开opc格式的文件
      doc = new XWPFDocument(pkg);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 段落,一个回车符就是1段
    Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
    int i = 0;
    while (iterator.hasNext()) {

      XWPFParagraph paragraph = iterator.next();
      // 获取小段,一个逗号是一小段
      List<XWPFRun> runs = paragraph.getRuns();

      for (XWPFRun run : runs) {
        System.out.println(run);
        // 当前run在runs中的索引值
        // int pos = run.getTextPosition();
        // String text = run.getText(pos);
        //
        // if (StringUtils.isNotEmpty(text)) {
        // for (Entry<String, String> e : map.entrySet()) {
        // if(text.equals(e.getKey())){
        // //0表示替换的开始索引
        // run.setText(e.getKey(),0);
        // }
        // }
        //
        // }

        // System.out.println(text);
        // int textPosition = xwpfRun.getTextPosition();
        // System.out.println(textPosition); //==>-1
        // System.out.println(xwpfRun);
      }
    }
    System.out.println(i);

  }

}
