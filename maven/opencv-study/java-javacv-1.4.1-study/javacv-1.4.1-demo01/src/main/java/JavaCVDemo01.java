import javax.swing.WindowConstants;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.VideoInputFrameGrabber;

public class JavaCVDemo01 {
  public static void main(String[] args) throws Exception, InterruptedException {
    JavaCVDemo01 demo01 = new JavaCVDemo01();
    demo01.testCamera1();
  }

  public void testCamera() throws InterruptedException, FrameGrabber.Exception {
    @SuppressWarnings("resource")
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    grabber.start(); // 开始获取摄像头数据
    CanvasFrame canvas = new CanvasFrame("摄像头");// 新建一个窗口
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设退出时关闭摄frame
    canvas.setAlwaysOnTop(true);
    while (true) {
      if (!canvas.isDisplayable()) {// 窗口是否关闭
        grabber.stop();// 停止抓取
        System.exit(-1);// 退出
      }
      Frame frame = grabber.grab();
      // 获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
      canvas.showImage(frame);
      Thread.sleep(50);// 50毫秒刷新一次图像
    }
  }

  public void testCamera1() throws FrameGrabber.Exception, InterruptedException {
    VideoInputFrameGrabber grabber = VideoInputFrameGrabber.createDefault(0);
    grabber.start();
    CanvasFrame canvasFrame = new CanvasFrame("摄像头");
    canvasFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    canvasFrame.setAlwaysOnTop(true);
    while (true) {
      if (!canvasFrame.isDisplayable()) {
        grabber.stop();
        System.exit(-1);
      }
      Frame frame = grabber.grab();
      canvasFrame.showImage(frame);
      Thread.sleep(30);
    }
  }
}