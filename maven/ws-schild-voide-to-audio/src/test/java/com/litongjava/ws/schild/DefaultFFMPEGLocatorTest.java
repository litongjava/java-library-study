package com.litongjava.ws.schild;

import org.junit.Test;

import ws.schild.jave.process.ffmpeg.DefaultFFMPEGLocator;

public class DefaultFFMPEGLocatorTest {

  @Test
  public void getgetExecutablePath() {
    DefaultFFMPEGLocator ffmpegLocator = new DefaultFFMPEGLocator();
    String executablePath = ffmpegLocator.getExecutablePath();
    System.out.println(executablePath);
  }
}
