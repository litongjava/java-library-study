package com.litongjava.sutdy.opencv.sample;

import java.util.Arrays;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import com.litongjava.sutdy.opencv.util.OpenCVLibraryUtil;

/**
 * �����ȶ�
 */
public class Sample03OfFaceCompare {
  // ��ʼ������̽����
  static CascadeClassifier faceDetector;

  public static void main(String[] args) {
    // ���ؿ��ļ�
    OpenCVLibraryUtil.init();
    // ��ʼ�������
    String openCVDir = "D:\\dev_progream\\opencv-4.1.1";
    String frontalfaceAltXml = openCVDir + "\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    faceDetector = new CascadeClassifier(frontalfaceAltXml);

    String basePicPath = "D:\\FaceOpenCVData\\";
    double compareHist = compareImage(basePicPath + "001.jpg", basePicPath + "duboo�����Ŷ�.jpg");
    System.out.println(compareHist);
    if (compareHist > 0.72) {
      System.out.println("����ƥ��");
    } else {
      System.out.println("������ƥ��");
    }
  }

  /**
   * �Ƚϰ�������������ͼƬ,����ͼƬ�����ƶ�
   */
  public static double compareImage(String src, String dst) {
    Mat mat_1 = convertToMat(src);
    Mat mat_2 = convertToMat(dst);

    // ��ɫ��Χ
    MatOfFloat ranges = new MatOfFloat(0f, 256f);
    // ֱ��ͼ��С�� Խ��ƥ��Խ��ȷ (Խ��)
    MatOfInt histSize = new MatOfInt(1000);

    Mat hist_1 = new Mat();
    Mat hist_2 = new Mat();
    Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), new Mat(), hist_1, histSize, ranges);
    Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0), new Mat(), hist_2, histSize, ranges);

    // CORREL ���ϵ��
    double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
    return res;
  }

  /**
   * 1.�ҶȻ� 2.ȡ��ͼƬ�а������������� ������
   */
  public static Mat convertToMat(String imagePath) {
    Mat image0 = Imgcodecs.imread(imagePath);
    Mat image1 = new Mat();
    // �ҶȻ�
    Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
    // ̽������
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image1, faceDetections);
    // ȡ��rect������ͼƬ�ķ�Χ,������
    for (Rect rect : faceDetections.toArray()) {
      Mat face = new Mat(image1, rect);
      return face;
    }
    return null;
  }
}
