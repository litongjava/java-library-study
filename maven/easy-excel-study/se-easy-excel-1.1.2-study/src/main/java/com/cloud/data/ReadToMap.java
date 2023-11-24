package com.cloud.data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;

public class ReadToMap {
  public static void main(String[] args) {
    // 读取 excel 表格的路径
    String readPath = "工作簿1.xlsx";
    Sheet sheet = new Sheet(1, 0);
    try {
      EasyExcelFactory.readBySax(new BufferedInputStream(new FileInputStream(readPath)), sheet,
          new AnalysisEventListener<List<String>>() {

            @Override
            public void invoke(List<String> object, AnalysisContext context) {
              System.out.println(object);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }

          });
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
