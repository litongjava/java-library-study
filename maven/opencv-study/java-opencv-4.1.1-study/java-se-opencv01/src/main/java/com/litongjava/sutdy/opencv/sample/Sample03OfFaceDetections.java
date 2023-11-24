package com.litongjava.sutdy.opencv.sample;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import com.litongjava.sutdy.opencv.util.OpenCVLibraryUtil;
import com.litongjava.sutdy.opencv.util.OpenCVMatUtil;

/**
 * 
 * @author litongjava ���ͼƬ���Ƿ��������
 *
 */
public class Sample03OfFaceDetections {
  public static void main(String[] args) {
    OpenCVLibraryUtil.init();
    faceDetection();
  }

  private static void faceDetection() {
    // ��ȡ����ͷ��Ƶ��
    VideoCapture capture = new VideoCapture(0);
    MatPanel panel = new MatPanel();

    int height = (int) capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
    int width = (int) capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
    if (height == 0 || width == 0) {
      System.out.println("camera not found! exit");
      System.exit(0);
    }

    JFrame frame = new JFrame("camera");
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setContentPane(panel);
    frame.setVisible(true);
    Insets insets = frame.getInsets();
    // ���ÿ���͸߶�
    frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);

    Mat mat = new Mat();
    while (frame.isShowing()) {
      // ��ȡ��Ƶ֡
      capture.read(mat);
      // ʶ������
      mat = detectFace(mat);
      // תΪͼ����ʾ
      panel.setBufferedImage(mat2BufferImage(mat));
      // repaint�Զ�����paint
      panel.repaint();
    }
    capture.release();
    frame.dispose();
  }

  /**
   * ת��ͼ��
   */
  private static BufferedImage mat2BufferImage(Mat mat) {
    int dataSize = mat.cols() * mat.rows() * (int) mat.elemSize();
    byte[] data = new byte[dataSize];
    mat.get(0, 0, data);

    int type = mat.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;
    if (type == BufferedImage.TYPE_3BYTE_BGR) {
      for (int i = 0; i < dataSize; i += 3) {
        byte blue = data[i + 0];
        data[i + 0] = data[i + 2];
        data[i + 2] = blue;
      }
    }
    BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
    image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

    return image;
  }

  /**
   * opencvʵ������ʶ��ͬʱ��⵽����������ʱ�Ž�ͼ
   */
  public static Mat detectFace(Mat mat) {
    // ����2���������ģ��,faceDetector��eyeDetector,����������۾�
    String openCVDir = "D:\\dev_progream\\opencv-4.1.1";
    String frontalfaceAltXml = openCVDir + "\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    String eyeXml = openCVDir + "\\sources\\data\\haarcascades\\haarcascade_eye.xml";
    CascadeClassifier faceDetector = new CascadeClassifier(frontalfaceAltXml);
    CascadeClassifier eyeDetector = new CascadeClassifier(eyeXml);

    // ��ͼƬ�м������,��������浽faceDetections
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(mat, faceDetections);

    Rect[] rects = faceDetections.toArray();
    if (rects != null && rects.length >= 1) {
      for (Rect rect : rects) {
        // ������
        Point leftTop = new Point(rect.x, rect.y);
        Point rightDown = new Point(rect.x + rect.width, rect.y + rect.height);
        Scalar color = new Scalar(0, 0, 255);
        Imgproc.rectangle(mat, leftTop, rightDown, color, 2);

        // ʶ������
        Mat faceROI = new Mat(mat, rect);
        MatOfRect eyesDetections = new MatOfRect();
        eyeDetector.detectMultiScale(faceROI, eyesDetections);
        // ���ʶ������,�򱣴�����
        if (eyesDetections.toArray().length > 1) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
          String format = sdf.format(new Date());
          String fileName = "face_detect_photo_" + format + ".jpg";
          System.out.println("detect face save:" + fileName);
          OpenCVMatUtil.saveMat(mat, rect, fileName);
        }
      }
    }
    return mat;
  }
}