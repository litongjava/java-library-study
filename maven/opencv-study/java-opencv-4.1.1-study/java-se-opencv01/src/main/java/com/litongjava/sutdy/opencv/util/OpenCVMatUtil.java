package com.litongjava.sutdy.opencv.util;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCVMatUtil {
  private static String folderName = "photo";

  /**
   * ������������ͼ
   */
  public static void saveMat(Mat img, Rect rect, String fileName) {
    File file = new File(folderName);
    if (!file.exists()) {
      file.mkdirs();
    }
    // ȡ��mat�а�������������
    Mat sub = img.submat(rect);
    // ���ô�С,����������浽�µ�mat��
    //Mat mat = new Mat();
    //Size size = new Size(300, 300);
    //Imgproc.resize(sub, mat, size);
    // �����ļ�
    Imgcodecs.imwrite(folderName + "/" + fileName, sub);
  }
}
