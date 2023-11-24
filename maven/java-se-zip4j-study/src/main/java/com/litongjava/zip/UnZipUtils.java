package com.litongjava.zip;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * @Auther: litong
 * @Date: 2018/6/28 10:03
 * @Description:zip文件解压缩工具类
 */
public class UnZipUtils {

  /**
   * @param source   原始文件路径
   * @param source   源文件编码,例如,"GBK"
   * @param dest     解压路径
   * @param password 解压文件密码(可以为空)
   * @throws ZipException
   */
  public static void unZip(String source, String charsetName, String dest, String password) throws ZipException {
    File destDir = new File(dest); // 解压目录
    if (!destDir.exists()) {// 目标目录不存在时，创建该文件夹
      destDir.mkdirs();
    }

    File zipFile = new File(source);
    // 首先创建ZipFile指向磁盘上的.zip文件
    ZipFile zFile = new ZipFile(zipFile);
    zFile.setFileNameCharset(charsetName);
    if (zFile.isEncrypted()) {
      zFile.setPassword(password.toCharArray()); // 设置密码
    }
    // 将文件抽出到解压目录(解压)
    zFile.extractAll(dest);
    // 获取压缩文件源数据信息
//   @SuppressWarnings("unchecked")
//    List<FileHeader> headerList = zFile.getFileHeaders();

    // 获取解压文件 列表
//    List<File> extractedFileList = new ArrayList<File>();
//    for (FileHeader fileHeader : headerList) {
//      if (!fileHeader.isDirectory()) {
//        extractedFileList.add(new File(destDir, fileHeader.getFileName()));
//      }
//    }

//    for (File f : extractedFileList) {
//      System.out.println(f.getAbsolutePath() + " 文件解压成功!");
//    }
  }
}