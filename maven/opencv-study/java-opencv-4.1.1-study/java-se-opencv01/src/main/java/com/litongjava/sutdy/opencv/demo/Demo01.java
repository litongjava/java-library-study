package com.litongjava.sutdy.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import com.litongjava.sutdy.opencv.util.OpenCVLibraryUtil;

public class Demo01 {
  public static void main(String[] args) {
    OpenCVLibraryUtil.init();
    Mat src = Imgcodecs.imread("D:\\PlateDetect\\person\\flower.png");
    if (src.empty()) {
      System.out.println("empty");
      return;
    }
    HighGui.imshow("input", src);
    HighGui.waitKey(0);
    HighGui.destroyAllWindows();
  }
}
