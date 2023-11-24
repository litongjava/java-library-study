package com.litong.java.se.playler;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.omg.IOP.TAG_CODE_SETS;

/**
 * runtime 类构造方法私有;--->单例模式;  
 * @author litong
 * @date 2020年9月16日 上午11:57:28
 * @desc
 */

public class showLove {
  public static boolean falg = true;

  public static void main(String[] args) throws IOException {
// 使用了单例模式; 使用Runtime 中自带的exec 方法,以command 命令的方式打开window下的程序

    Runtime rt = Runtime.getRuntime();
    rt.exec("notepad");
    Thread t = new Thread(() -> {
      PlayerUtils.play("D:\\dev_workspace\\java\\hg_project\\bill-biaobei-tts-agent\\48000.mp3");
      // 单线程要一行一行的执行，所以如果为单线程，就会播放音乐完成后再播放音乐；
    });
    t.start(); // 开启音乐线程;
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 读取文件;
    readFile("D:\\document\\k.学习资料总结\\40.自考\\01人力资源管理-专科\\管理学\\电子书\\00.txt");
    //强行终止进程以达到结束两个线程的;
    System.out.println(t.isAlive());
  }

// 判断主线程是否执行完毕;
  public static boolean judgeOfThread() {
    return true;
  }

  /**
  * 读取文件
  * @param path //文件的路径
  */
  public static void readFile(String path) {
    // 自动关闭try with resources; JDK1.7新特性
    try (FileReader fr = new FileReader(path)) {
      int c;
      while ((c = fr.read()) != -1) {
        // 将读取的部分转化为字符串;
        copyAndPaste(String.valueOf((char) c));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
  * 将数据粘贴到文件中;
  * @param str
  */
  public static void copyAndPaste(String str) {
    // 创建StringSelection对象,将str() 放入;

    StringSelection stringSelection = new StringSelection(str);

    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
//       ctrl V;---.模拟ctrl v 的执行;

    Robot robot = null;
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
// 两个键的按下;
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
//键盘的松开;
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
// 随机的delay() 延迟;
    Random random = new Random();
    int delay = random.nextInt(600); // 随机数的边界
    if (delay < 100) {
      delay += 200;
    }
    robot.delay(delay); // 单位为毫秒;
  }
}