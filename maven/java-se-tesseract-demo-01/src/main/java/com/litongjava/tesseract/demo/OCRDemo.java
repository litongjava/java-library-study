package com.litongjava.tesseract.demo;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class OCRDemo {
  public static void main(String args[]) throws Exception {
    //String filename = "D:\\PlateDetect\\06_文字\\Snipaste_2021-11-08_11-18-14.png";
    String filename="D:\\PlateDetect\\08_物联网比赛真实车牌\\车牌-距离-0-hsv.jpg";
    //String filename="D:\\PlateDetect\\08_物联网比赛真实车牌\\车牌-距离-0_hsv_gary.jpg"; //识别为乱码
    ocr(filename);
  }

  private static void ocr(String filename) {
    ITesseract instance = new Tesseract();
    instance.setDatapath(System.getenv("TESSDATA_PREFIX"));
    //instance.setLanguage("chi_sim");
    instance.setLanguage("eng");
    File imageFile = new File(filename);
    try {
      String result = instance.doOCR(imageFile);
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}