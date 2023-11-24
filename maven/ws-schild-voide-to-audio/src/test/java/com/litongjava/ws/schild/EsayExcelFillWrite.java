package com.litongjava.ws.schild;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.litongjava.jfinal.models.voidetotext.model.VipReportDtoOut;
import com.litongjava.utils.reflection.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EsayExcelFillWrite {

  @Test
  public void fillWrite() {
    //获取数据
    List<VipReportDtoOut> data = getData();
    // 模板地址
    InputStream inputStream = getInputStream();
    
    File file = new File("导出数据.xlsx");
    ExcelWriterBuilder builder = EasyExcel.write(file);
    ExcelWriter excelWriter = builder.withTemplate(inputStream).build();

    WriteSheet writeSheet = EasyExcel.writerSheet().build();

    // 填充集合 data
    try {
      excelWriter.fill(data, writeSheet);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 查询条件
    Map<String, Object> map = new HashMap<String, Object>();
    StringBuffer sb = new StringBuffer();
    sb.append("时间段：2020-01-01至2020-02-01");
    map.put("queryTerms", sb.toString());
    excelWriter.fill(map, writeSheet);

    // 合计部分
    BigDecimal totalAmount = new BigDecimal(0);
    Long totalNum = 0L;
    for (VipReportDtoOut vipReportDtoOut : data) {
      totalAmount = totalAmount.add(vipReportDtoOut.getAmount());
      totalNum += vipReportDtoOut.getNum();
    }

    VipReportDtoOut totalPay = new VipReportDtoOut();
    totalPay.setAmount(totalAmount);
    totalPay.setNum(totalNum);

    List<String> totalList = new ArrayList<String>();
    totalList.add("合计：");
    totalList.add(totalPay.getAmount().toString());
    totalList.add(totalPay.getNum().toString());

    List<List<String>> totalListList = new ArrayList<List<String>>();
    totalListList.add(totalList);

    excelWriter.write(totalListList, writeSheet);
    // 关闭流
    excelWriter.finish();
  }
  

  private List<VipReportDtoOut> getData() {
    List<VipReportDtoOut> data = new ArrayList<>();
    VipReportDtoOut vipReportDtoOut0 = new VipReportDtoOut("2020-01-01", new BigDecimal(1000), 1L);
    VipReportDtoOut vipReportDtoOut1 = new VipReportDtoOut("2020-01-02", new BigDecimal(2000), 2L);

    data.add(vipReportDtoOut0);
    data.add(vipReportDtoOut1);
    return data;
  }
  
  private InputStream getInputStream() {
    String templateFileName = "template" + File.separator + "xls" + File.separator + "vipReport.xlsx";
    log.info("模板文件:{}", templateFileName);
    InputStream inputStream = new ClassPathResource(templateFileName).getInputStream();
    log.info("inputStream:{}", inputStream);
    return inputStream;
  }
}

