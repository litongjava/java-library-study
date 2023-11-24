package com.litongjava.zip;

import org.junit.Test;

import net.lingala.zip4j.exception.ZipException;

public class UnZipUtilsTest {

  @Test
  public void test() throws ZipException {
    UnZipUtils z = new UnZipUtils();
    String source = "G:\\package\\1+x 考题\\考题.zip";
    String dest = "D:\\";
    String password = "499510";
    UnZipUtils.unZip(source, "GBK", dest, password);
  }
}
