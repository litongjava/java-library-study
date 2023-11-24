package com.litongjava.study.se.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListDirToMavenModule {
  public static void main(String[] args) {
    String path="E:\\dev_workspace\\java\\java-study\\java-se-study";
    start(path);
  }

  private static void start(String path) {
    File mavenParentPathFile = new File(path);
    List<String> moduleNameList=new ArrayList<>();
    File[] listFiles = mavenParentPathFile.listFiles();
    for (File file : listFiles) {
      if(file.isDirectory()){
        String dirName = file.getName();
        if(!dirName.startsWith(".")){
          moduleNameList.add(file.getName());
        }
      }
    }
    StringBuffer outputContent=new StringBuffer();
    for (String s : moduleNameList) {
      outputContent.append("<module>").append(s).append("</module>").append("\n");
    }
    System.out.println(outputContent);
  }
}
