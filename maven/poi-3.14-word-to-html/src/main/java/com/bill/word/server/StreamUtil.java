package com.bill.word.server;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

  public static InputStream newFileInputStream(String path) {
    InputStream input = null;
    try {
      input = new FileInputStream(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return input;
  }

  public static void close(Closeable io) {
    try {
      io.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
