package com;

import java.io.File;
import java.net.URL;

import com.utils.JaveUtil;

import ws.schild.jave.MultimediaInfo;

public class Main {
  public static void main(String[] args) {
    getMultimediaInfo();
    mp32wav();
    // mp32Mp3();
  }

  private static void mp32Mp3() {
    URL resource = Main.class.getClassLoader().getResource("public_audio");
    String mp3Path = resource.getFile() + File.separator + "16k.mp3";
    String wavPath = resource.getFile() + File.separator + "48k.mp3";
    JaveUtil.mp32Mp3(mp3Path, wavPath, 48000, 1, 768000);
    System.out.println(wavPath);
  }

  private static void mp32wav() {
    URL resource = Main.class.getClassLoader().getResource("public_audio");
    String mp3Path = resource.getFile() + File.separator + "16k.mp3";
    String wavPath = resource.getFile() + File.separator + "48k.wav";
    JaveUtil.mp32Wav(mp3Path, wavPath, 48000, 1, 768000);// 768000=48000*16*1
    System.out.println(wavPath);
  }

  private static void getMultimediaInfo() {
    URL resource = Main.class.getClassLoader().getResource("public_audio/16k.wav");
    String path = resource.getFile();
    MultimediaInfo mediaInfo = JaveUtil.getMediaInfo(path);
    System.out.println(mediaInfo);
  }

}
