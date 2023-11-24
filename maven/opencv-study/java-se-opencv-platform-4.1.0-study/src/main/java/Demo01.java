import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class Demo01 {
  public static void main(String[] args) {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    String testImg = "D:/PlateDetect/test/13.png";
    Mat src = Imgcodecs.imread(testImg);
    HighGui.imshow("input", src);
    HighGui.waitKey(0);
    HighGui.destroyAllWindows();
  }
}
