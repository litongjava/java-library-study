package com.litongjava.zip;

import net.lingala.zip4j.exception.ZipException;

public class UnzipFileExhaustivityDemo {
  public static void main(String[] args) {
    // 假设密码是6位数字
    for (int i = 0; i < 1000000; i++) {
      String canBeAPassowrd = String.format("%06d", i);
      String source = "G:\\package\\1+x 考题\\考题.zip";
      String dest = "D:\\";
      try {
        UnZipUtils.unZip(source, "GBK", dest, canBeAPassowrd);
        System.out.println("正确密码:" + canBeAPassowrd);
        return;
      } catch (ZipException e) {
        System.out.println("错误密码:" + canBeAPassowrd);
      }
    }
  }
}
