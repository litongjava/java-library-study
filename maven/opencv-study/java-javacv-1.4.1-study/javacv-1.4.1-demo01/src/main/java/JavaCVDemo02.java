import java.io.File;
import java.net.MalformedURLException;

import javax.swing.WindowConstants;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class JavaCVDemo02 {

  public static void main(String[] args)
      throws Exception, MalformedURLException, org.bytedeco.javacv.FrameRecorder.Exception, InterruptedException {
    JavaCVDemo02 demo02 = new JavaCVDemo02();
    demo02.testFaceRecognize();
  }

  private void showFramesWithFace(String winTitle, FrameGrabber grabber) throws FrameGrabber.Exception, InterruptedException {
    // 1.配置人脸检测模型
    String xmlPath = "data/lbpcascade_frontalface_improved.xml";
    File file = new File(ClassLoader.getSystemClassLoader().getResource(xmlPath).getFile());
    // 人脸检测模型
    opencv_objdetect.CascadeClassifier face_cascade = new opencv_objdetect.CascadeClassifier(file.getAbsolutePath());
    // 2.配置CanvasFrame
    CanvasFrame canvas = new CanvasFrame(winTitle, 1);// 新建一个窗口
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    canvas.setAlwaysOnTop(true);
    // 3.配置Frame和Map互相转换的转换器
    OpenCVFrameConverter.ToMat convertToMat = new OpenCVFrameConverter.ToMat();
    // 4.配置faceRect,保存人脸大小
    opencv_core.RectVector faceRect = new opencv_core.RectVector();

    while (true) {
      if (!canvas.isVisible()) {
        break;
      }
      // 获取frame数据
      Frame frame = grabber.grab();
      // 转为mat数据
      Mat mat = convertToMat.convert(frame);
      if (mat.empty())
        continue;
      Mat videoMatGray = new Mat();
      // 进行图片通道转换
      opencv_imgproc.cvtColor(mat, videoMatGray, opencv_imgproc.COLOR_BGRA2GRAY);
      // 直方图均衡化
      opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);
      // 进行人脸检测
      face_cascade.detectMultiScale(videoMatGray, faceRect);
      for (int i = 0; i < faceRect.size(); i++) {
        opencv_core.Rect face = faceRect.get(i);
        //画红色的矩形
        opencv_imgproc.rectangle(mat, face, opencv_core.Scalar.RED, 4, 8, 0);
      }
      //转成frame,进行显示
      Frame resultFrmae = convertToMat.convert(mat);
      canvas.showImage(resultFrmae);
      Thread.sleep(30);// 50毫秒刷新一次图像
    }
    face_cascade.close();
  }

  public void testFaceRecognize()
      throws FrameGrabber.Exception, InterruptedException, MalformedURLException, FrameRecorder.Exception {
    OpenCVFrameGrabber grabber = OpenCVFrameGrabber.createDefault(0);
    grabber.start();
    showFramesWithFace("Video", grabber);
    grabber.stop();
    grabber.close();
  }
}