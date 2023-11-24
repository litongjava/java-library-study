package com.litongjava.ws.schild;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.litongjava.ws.schild.model.FillData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EsayExcelFillWiteMap {

  @Test
  public void fill() {
    // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"{","}"代替
    String templateFileName = "template" + File.separator + "xls" + File.separator + "name-number-template.xlsx";
    File templateFile = new File(templateFileName);
    log.info("templateFile:{}", templateFile);

    // 方案1 根据对象填充
    String fileName = System.currentTimeMillis() + ".xlsx";
    log.info("fileName:{}", fileName);
    // 这里 会填充到第一个sheet， 然后文件流会自动关闭
    FillData fillData = new FillData();
    fillData.setName("张三");
    fillData.setNumber(5);
    EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);

    // 方案2 根据Map填充
    fileName = System.currentTimeMillis() + ".xlsx";
    log.info("fileName:{}", fileName);
    // 这里 会填充到第一个sheet， 然后文件流会自动关闭
    Map<Object, Object> map = new HashMap<>();
    map.put("name", "张三");
    map.put("number", 5);
    EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
  }
}
