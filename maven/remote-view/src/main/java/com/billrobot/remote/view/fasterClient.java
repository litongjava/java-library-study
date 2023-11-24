package com.billrobot.remote.view;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class fasterClient {
  private static Socket socket;
  private static ObjectOutputStream OOS;
  private static ObjectInputStream OIS;
  private static Robot robot;

  public static void main(String[] args) throws UnknownHostException, IOException, AWTException, InterruptedException {

    try {
      StartConnection("s", 1);
      robotThread robot = new robotThread(OIS);
      Thread t = new Thread(robot, "robot");
      t.start();
      while (true) {
        CapturePic();
      }
    } catch (Exception a) {
      OOS.close();
      socket.close();
    }
  }

  public static void StartConnection(String IP, int port) throws UnknownHostException, IOException, AWTException {
    socket = new Socket("192.168.99.214", 7777);
    if (socket.isConnected()) {
      System.out.println("socket connected..." + socket);
    }
    OOS = new ObjectOutputStream(socket.getOutputStream());
    OIS = new ObjectInputStream(socket.getInputStream());

  }

  public static void CapturePic() throws AWTException, IOException {
    robot = new Robot();
    Message msg = null;
    Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
    java.awt.Dimension dm = tk.getScreenSize();
    java.awt.Robot robot = new java.awt.Robot();
    for (int i = 0; i < 50; i++) {
      // ��ȡָ����С����Ļ����
      Rectangle rec = new Rectangle(0, 0, (int) dm.getWidth(), (int) dm.getHeight());
      BufferedImage bimage = robot.createScreenCapture(rec);
      // ��ͼƬ���浽�ļ���
      String filePath = "D:\\OUT\\screenshot" + i + ".jpeg";
      FileOutputStream fops = new FileOutputStream(filePath);
      javax.imageio.ImageIO.write(bimage, "jpeg", fops);
      fops.flush();
      fops.close();
      msg = new Message(filePath);

      System.out.println(msg.getFileName());
      System.out.println("send");
      OOS.writeObject(msg);
      OOS.flush();

    }
  }

  public static void Close() throws IOException {
    OOS.flush();
    OOS.close();
    socket.close();

  }

}
