package com.litongjava.sutdy.opencv.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import com.litongjava.sutdy.opencv.util.OpenCVLibraryUtil;
import com.litongjava.sutdy.opencv.util.OpenCVMatUtil;

public class Sample01OfFaceDetections {

  public static void main(String[] args) {
    OpenCVLibraryUtil.init();
    faceDetections();
  }

  private static void faceDetections() {
    // ������ͷ
    VideoCapture vc = new VideoCapture(0);
    // ����ģ���ļ�,�������ģ��
    String openCVDir = "D:\\dev_progream\\opencv-4.1.1";
    String xmlPath = openCVDir + "\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_default.xml";
    CascadeClassifier faceDetector = new CascadeClassifier(xmlPath);
    // ��ȡ��Ƶ֡,����mat��
    Mat mat = new Mat();
    while (vc.read(mat)) {
      // ʹ���������ģ��,���mat�������Ƿ�������,�����⵽��������⵽���������ݴ洢��faceDetections��
      MatOfRect faceDetections = new MatOfRect();
      faceDetector.detectMultiScale(mat, faceDetections);
      // �����⵽����������
      System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

      for (Rect rect : faceDetections.toArray()) {
        // ��������������
        Point leftTop = new Point(rect.x, rect.y);
        // ��������������
        Point rightDown = new Point(rect.x + rect.width, rect.y + rect.height);
        // ��ɫ
        Scalar color = new Scalar(0, 255, 0);
        // ����һ��������mat��,ָ���������ɫ
        Imgproc.rectangle(mat, leftTop, rightDown, color);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String format = sdf.format(new Date());
        String fileName = "face_detect_photo_" + format + ".jpg";
        System.out.println("detect face save:" + fileName);
        OpenCVMatUtil.saveMat(mat, rect, fileName);
      }

      int key = showMat(mat);
      if (key == 27) {
        break;
      }
    }
    // ���ٴ���
    HighGui.destroyAllWindows();
    // �ر�����ͷ
    vc.release();
  }

  private static int showMat(Mat mat) {
    // ���岢���ô��ڵ�����
    String winName = "showFrame";
    HighGui.namedWindow(winName);
    // ���ô��ڵĴ�С
    HighGui.resizeWindow(winName, 800, 600);
    // ��������ʾͼƬ
    HighGui.imshow(winName, mat);
    // ���� waitKey,��ʾ����
    int key = HighGui.waitKey(1);
    return key;
  }
}