import javax.swing.WindowConstants;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

public class JavaCVDemo03 {
  public static void main(String[] args) throws FrameGrabber.Exception, FrameRecorder.Exception, InterruptedException {
    // Preload the opencv_objdetect module to work around a known bug.
    String str = Loader.load(opencv_objdetect.class);
    System.out.println("objdetect:" + str);

    FrameGrabber grabber = FrameGrabber.createDefault(0);
    grabber.start();
    // 抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
    Frame grabbedImage = grabber.grab();

    int width = grabbedImage.imageWidth;
    int height = grabbedImage.imageHeight;
    String outputFile = "d:\\record.mp4";
    // org.bytedeco.javacv.FFmpegFrameRecorder
    FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);

    System.out.println("recorder:" + recorder.getClass().getName());
    // avcodec.AV_CODEC_ID_H264，编码
    recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
    // 封装格式，如果是推送到rtmp就必须是flv封装格式
    recorder.setFormat("flv");
    recorder.setFrameRate(25);
    // 开启录制器
    recorder.start();
    
    long startTime = 0;
    long videoTS;
    CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma()); // 2.2/2.2=1
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setAlwaysOnTop(true);
    
    Frame rotatedFrame;
    while (frame.isVisible() && (rotatedFrame = grabber.grab()) != null) {
      frame.showImage(rotatedFrame);
      if (startTime == 0) {
        startTime = System.currentTimeMillis();
      }
      videoTS = (System.currentTimeMillis() - startTime) * 1000;// 这里要注意，注意位
      recorder.setTimestamp(videoTS);
      recorder.record(rotatedFrame);
      Thread.sleep(40);
    }
    recorder.stop();
    recorder.release();
    frame.dispose();
    grabber.stop();
    grabber.close();
  }
}