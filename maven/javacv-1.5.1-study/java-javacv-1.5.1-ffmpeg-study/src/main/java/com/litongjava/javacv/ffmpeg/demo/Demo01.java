package com.litongjava.javacv.ffmpeg.demo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

import com.litongjava.utils.digest.MD5Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author create by ping-e-lee on 2021年7月2日 下午4:48:43 
 * @version 1.0 
 * @desc
 */

@Slf4j
public class Demo01 {
  public static void main(String[] args) throws Exception {
    String filePath = "E:\\document\\k.学习资料总结\\02.english\\英文口语短视频\\0d7a074bb2730d4ac363eaf19c0149da.mp4";
    File videoFile = new File(filePath);
    /**
     * 获取路径的md5判断文件夹是否存在,如果不存在则创建
     */
    String encode = MD5Utils.encode(filePath);
    File imageFolder = new File("imgs" + File.separator + encode);
    if (!imageFolder.exists()) {
      imageFolder.mkdirs();
    }
    // 提取者
    @SuppressWarnings("resource")
    FFmpegFrameGrabber ffg = new FFmpegFrameGrabber(videoFile);
    // 开始提取
    ffg.start();
    Frame f = ffg.grabFrame();
    while (f != null) {
      // 提取关键帧
      System.out.println(f);
      // 若显示帧不为关键帧，则寻找相邻的下一帧
      if (f.image != null) {
        String filename = imageFolder+File.separator+appendTens(f.timestamp) + ".jpg";
        log.info("filename : " + filename);
        doExecuteFrame(f, filename);
      }
      f = ffg.grabFrame();
    }
  }

  /**
   * 将任意一个数补全为10位数
   * @param timestamp
   * @return
   */
  public static String appendTens(long timestamp) {
    if (timestamp == 0) {
      return "0000000000";
    }
    long max = 9999999999L;
    long ji = timestamp * 10;
    StringBuffer stringBuffer = new StringBuffer();
    while (max > ji) {
      stringBuffer.append("0");
      ji *= 10;
    }
    stringBuffer.append(timestamp);
    return stringBuffer.toString();
  }

  /**
   * 输出成本地图片
   */
  public static void doExecuteFrame(Frame f, String targetFileName) {
    if (null == f || null == f.image) {
      return;
    }
    Java2DFrameConverter converter = new Java2DFrameConverter();
    BufferedImage bi = converter.getBufferedImage(f);
    File output = new File(targetFileName);
    try {
      ImageIO.write(bi, "jpg", output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
