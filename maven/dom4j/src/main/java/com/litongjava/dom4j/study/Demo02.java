package com.litongjava.dom4j.study;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Demo02 {
  public static void main(String[] args) throws DocumentException, IOException {
//    test1();
    test2();
  }

  /**
   * 查找单个节点name,得到张三文本
   * @throws DocumentException
   */
  private static void test1() throws DocumentException {
    String xmlPath = "src/main/resources/Student.xml";
    // 创建SAXReader实例
    SAXReader r = new SAXReader();
    // 获取document对象
    Document doc = r.read(xmlPath);
    // 得到根节点
    Element root = doc.getRootElement();
    // element() 方法： 获取指定名称的第一个子节点对象(一层)
    Element stu = root.element("student");
    Element name = stu.element("name");
    // name.getText() 方法： 获取name节点的文本内容
    System.out.println(name.getText());
  }

  /**
   * 查询所有节点信息
   * @throws DocumentException
   */
  private static void test2() throws DocumentException {
    String xmlPath = "src/main/resources/Student.xml";
    Document doc = new SAXReader().read(xmlPath);
    Element root = doc.getRootElement();
    // 得到所有学生节点对象
    List<?> studentList = root.elements("student");
    // 迭代
    Iterator<?> iterator = studentList.iterator();
    System.out.println("姓名\t年龄\t性别");
    while (iterator.hasNext()) {
      Element s = (Element) iterator.next();
      String name = s.element("name").getText();
      String age = s.element("age").getText();
      String sex = s.element("sex").getText();
      System.out.println(name + "\t" + age + "\t" + sex);
    }
  }

  private static void test4() throws DocumentException, IOException {
    /**
     * 先读取xml成document
     * document操作 节点
     * 把document写成xm
     */
    String xmlPath = "src/main/resources/Student.xml";

    Document document = new SAXReader().read(xmlPath);
    Element rootElement = document.getRootElement();

    Element element = rootElement.addElement("student");
    Element nameElement = element.addElement("name");
    Element ageElement = element.addElement("age");
    Element sexElement = element.addElement("sex");

    nameElement.addText("王五");
    ageElement.addText("20");
    sexElement.addText("男");

    element.addAttribute("number", "s0004");
    // 写数据
    FileOutputStream fileOutputStream = new FileOutputStream(xmlPath);
    XMLWriter xmlWriter = new XMLWriter(fileOutputStream, OutputFormat.createPrettyPrint());
    xmlWriter.write(document);
    xmlWriter.close();
  }

  private static void test5() throws DocumentException, IOException {
    String xmlPath = "src/main/resources/Student.xml";
    Document document = new SAXReader().read(xmlPath);

    Element rootElement = document.getRootElement();
    List<?> studentList = rootElement.elements("student");
    Iterator<?> iterator = studentList.iterator();
    while (iterator.hasNext()) {
      Element elment = (Element) iterator.next();
      String name = elment.element("name").getText();
      if ("王五".equals(name)) {
        rootElement.remove(elment);
      }
    }
    FileOutputStream out = new FileOutputStream(xmlPath);
    XMLWriter xmlWriter = new XMLWriter(out, OutputFormat.createPrettyPrint());
    xmlWriter.write(document);
    xmlWriter.close();
  }
}
