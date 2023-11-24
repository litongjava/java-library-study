package com.litong.java.se.playler;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 播放音乐
 * 
 */
public class PlayerUtils {
  public static void play(String path) {
    // player是一个流对象;
    while (true) { // 没有办法立即停止;
      try {
        // 将音乐文件放到play；
        Player player = new Player(new FileInputStream((new File(path))));
        player.play(); // 播放音乐;
      } catch (JavaLayerException e) {
        e.printStackTrace();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}