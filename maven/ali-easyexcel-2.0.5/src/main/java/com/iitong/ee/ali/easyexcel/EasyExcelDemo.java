package com.iitong.ee.ali.easyexcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.util.IOUtils;

public class EasyExcelDemo {
  public static void main(String[] args) {
    EasyExcelDemo demo = new EasyExcelDemo();
    demo.testReadExcel();
  }

  public void testExcel() {
    // 定义标题
    List<String> head = Arrays.asList("isbn号", "书名");
    // 定义列表
    List<Book> books = Arrays.asList(new Book("isbn-1", "SpringMVC"), new Book("isbn-2", "Mybatis"));
    // 写入excel
    FileOutputStream out = null;
    try {
      out = new FileOutputStream("books_write.xlsx");
      ExcelUtil.writeListTo(out, books, Book.class, head);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(out);
    }
  }

  public void testReadExcel() {
    FileInputStream in = null;
    try {
      in = new FileInputStream("books_write.xlsx");
      List<Book> readBooks = ExcelUtil.readListFrom(in, Book.class);
      System.out.println(readBooks);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(in);
    }
  }
}
