package com.litongjava.study.se.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author create by ping-e-lee on 2021年6月23日 下午11:10:48 
 * @version 1.0 
 * @desc
 */
public class ArtifactIdDemo {

  /**
   * 检查ArtifactId获取文件名是否一致
   * <artifactId>java-se-jsoup-demo1</artifactId>
   * @param args
   * @throws DocumentException 
   */
//  private static String parentGrouupId = "com.litongjava";
//  private static String parentArtifaceId = "java-se-study";
//  private static String parentVersion = "1.0";
  private static String parentGrouupId = "com.litongjava";
  private static String parentArtifaceId = "java-ee-spring-boot-study";
  private static String parentVersion = "1.0";

  public static void main(String[] args) throws DocumentException {

    // String parentPath = "E:\\dev_workspace\\java\\java-study\\java-se-study";
    String parentPath = "E:\\dev_workspace\\java\\java-study\\java-ee-spring-boot-study";
    File mavenParentPathFile = new File(parentPath);
    List<String> moduleNameList=new ArrayList<>();
    File[] listFiles = mavenParentPathFile.listFiles();
    for (File file : listFiles) {
      if (file.isDirectory()) {
        String dirName = file.getName();
        if (dirName.startsWith(".") || dirName.equals("src")) {
          continue;
        } else {
          validateArtifactId(file);
          moduleNameList.add(file.getName());
        }
      }
    }
    //输出moddule内容
    StringBuffer outputContent=new StringBuffer();
    for (String s : moduleNameList) {
      outputContent.append("<module>").append(s).append("</module>").append("\n");
    }
    System.out.println(outputContent);
    
  }

  /**
   * 1.检查 pom.xml中的artifactId和文件名是否一致,如果不不一致修改pom.xml中的artifaceId
   * 2.检查 pom.xml中的parent是否存在,如果不存在,添加parent
   */
  private static void validateArtifactId(File file) throws DocumentException {
    String xmlPath = file.getAbsolutePath() + "/pom.xml";
    File xmlFile = new File(xmlPath);
    if (!xmlFile.exists()) {
      System.err.printf("%s not found \n", xmlFile.getAbsolutePath());
      return;
    }
    /**
     * 获取artifactId的值
     */
    System.out.printf("validate %s", xmlPath);
    SAXReader saxReader = new SAXReader();
    Document document = saxReader.read(xmlFile);
    Element rootElement = document.getRootElement();
    Element artifactIdElement = rootElement.element("artifactId");
    String text = artifactIdElement.getText();

    /**
     * 判断和文件名是否一致,如果不一致,修改
     */
    String filename = file.getName();
    if (!filename.equals(text)) {
      System.out.printf("%s \t %s \n", filename, text);
      artifactIdElement.setText(filename);
    }
    /**
     * 检查parent
     */
    Element parentElement = rootElement.element("parent");
    System.out.println(parentElement);// parentElment为null表示元素不存在

    if (parentElement == null) {
      addParent(rootElement, parentGrouupId, parentArtifaceId, parentVersion);
    } else {
      updateParent(parentElement, parentGrouupId, parentArtifaceId, parentVersion);
    }

    // 保存文件
    Dom4jUtils.write(document, xmlPath);

  }

  private static void updateParent(Element parentElement, String parentGrouupId, String parentArtifactId, String parentVersion) {
    Element groupdIdElement = parentElement.element("groupId");
    groupdIdElement.setText(parentGrouupId);
    Element artifactIdElement = parentElement.element("artifactId");
    artifactIdElement.setText(parentArtifactId);
    Element versionElement = parentElement.element("version");
    versionElement.setText(parentVersion);
  }

  /**
   * 添加parent
   * @param rootElement
   * @param parentGrouupId2
   * @param parentArtifaceId2
   * @param parentVersion2
   */
  private static void addParent(Element rootElement, String parentGrouupId, String parentArtifactId, String parentVersion) {
    Element parentElement = rootElement.addElement("parent");
    Element groupIdElement = parentElement.addElement("groupId");
    groupIdElement.setText(parentGrouupId);
    Element artifactIdElement = parentElement.addElement("artifactId");
    artifactIdElement.setText(parentArtifactId);
    Element versionElement = parentElement.addElement("version");
    versionElement.setText(parentVersion);

  }
}
