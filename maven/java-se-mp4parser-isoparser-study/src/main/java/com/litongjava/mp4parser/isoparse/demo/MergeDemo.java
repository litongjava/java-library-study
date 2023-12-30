package com.litongjava.mp4parser.isoparse.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.litongjava.mp4parser.isoparse.utils.Mp4ParserUtils;
import com.litongjava.utils.file.filter.ExtensionFilenameFilter;

public class MergeDemo {
  public static void main(String[] args) {
    //获取文件列表
    String videoFilePath="G:\\video\\英语作业-愚公移山-拍摄视频";
    String dstVideoPath="G:\\video\\英语作业-愚公移山-拍摄视频";
    File dstVideoFile = new File(dstVideoPath);
    ExtensionFilenameFilter filter = new ExtensionFilenameFilter(".mp4");
    File videoFile = new File(videoFilePath);
    File[] voidFiiles = videoFile.listFiles(filter);
    List<String> videoFilePathList=new ArrayList<>();
    for (File file : voidFiiles) {
      System.out.println(file.getAbsolutePath());
      videoFilePathList.add(file.getAbsolutePath());
    }
    
    try{
      Mp4ParserUtils.mergeVideo(videoFilePathList,dstVideoFile);  
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}
