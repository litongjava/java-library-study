package com.billrobot.remote.view;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class RobotTest {
  // 截图工具
  private static Robot robot = null;
  // 获取分辨与工具
  private static Toolkit toolkit = null;
  static {
    try {

      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
    toolkit = Toolkit.getDefaultToolkit();
  }

  @Test
  public void createScreenCapture() {
    // 创建和屏幕大小相同的矩形
    Rectangle rectangle = new Rectangle(0, 0, toolkit.getScreenSize().width, toolkit.getScreenSize().height);
    // 捕捉桌面
    BufferedImage image = robot.createScreenCapture(rectangle);
    // 这边吧照片转换输出
    try {
      ImageIO.write(image, "jpeg", new FileOutputStream(("00.jpeg")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
