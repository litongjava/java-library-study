package com.litongjava.ws.schild;

import com.alibaba.excel.EasyExcel;
import com.litongjava.jfinal.models.voidetotext.model.Student;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by litonglinux@qq.com on 12/2/2021_8:52 AM
 */
public class EasyExceTest {
  public List<Student> getData() {
    List<Student> lists = new ArrayList<>();
    for (int i = 0; i <= 10; i++) {
      Student student = new Student();
      student.setId(i + 1);
      student.setName("李四" + i);
      student.setBirthday(new Date());
      student.setSalary(1500.00D);
      lists.add(student);
    }
    return lists;
  }

  @Test
  public void wirteModel() {
    try {
      EasyExcel.write("学生信息表.xlsx", Student.class).sheet().doWrite(getData());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void writeMapToExcel() {
    Map<String, String> map = new HashMap<>();
    map.put("001.pcm", "今天,我们来讲计算机基础");
    map.put("002.pcm", "请大家打开课本");

    List<Map<String, String>> list = new ArrayList<>(1);
    list.add(map);
    File file = new File("转写信息表-1.xlsx");
    EasyExcel.write(file).sheet().doWrite(list);
  }
}
