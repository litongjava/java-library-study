package com.litongjava.ws.schild.filter;

import java.io.File;

import org.junit.Test;

import com.litongjava.utils.file.filter.ExtensionFilenameFilter;

public class ExtensionFilenameFilterTest {

  @Test
  public void testExtensionFilenameFilter() {
    String path = "H:\\video\\软件开发学习视频\\微信小程序\\第1章";
    ExtensionFilenameFilter extensionFilenameFilter = new ExtensionFilenameFilter(".mp3");

    File pathFile = new File(path);
    String[] list = pathFile.list(extensionFilenameFilter);
    for (String string : list) {
      System.out.println(string);
    }
  }
}
