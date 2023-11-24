package com.litongjava.ws.schild;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.EasyExcel;
import com.litongjava.jfinal.models.voidetotext.model.VipReportDtoOut;

public class EasyExcelDynamicWrite {

  @Test
  public void wirte() {
    List<List<String>> heads = getHead();
    // 表格数据
    List<List<Object>> dataList = getDate();
    File file = new File("导出表格.xlsx");
    EasyExcel.write(file).head(heads).sheet().doWrite(dataList);

  }

  private List<List<String>> getHead() {
    // 表头
    List<List<String>> heads = new ArrayList<List<String>>();

    StringBuffer sb = new StringBuffer("查询条件：");
    sb.append("时间段：2020-01-01至2020-02-01");

    List<String> head0 = new ArrayList<String>();
    head0.add(sb.toString());
    head0.add("日期");
    List<String> head1 = new ArrayList<String>();
    head1.add(sb.toString());
    head1.add("包月金额");
    List<String> head2 = new ArrayList<String>();
    head2.add(sb.toString());
    head2.add("包月数量");

    heads.add(head0);
    heads.add(head1);
    heads.add(head2);
    return heads;
  }

  private List<List<Object>> getDate() {
    // List<VipReportDtoOut> report = (List<VipReportDtoOut>)
    // params.get("reportList"); // 查询数据
    List<VipReportDtoOut> report = new ArrayList<>();
    VipReportDtoOut vipReportDtoOut0 = new VipReportDtoOut("2020-01-01", new BigDecimal(1000), 1L);
    report.add(vipReportDtoOut0);
    VipReportDtoOut vipReportDtoOut1 = new VipReportDtoOut("2020-01-01", new BigDecimal(2000), 2L);
    report.add(vipReportDtoOut1);

    List<List<Object>> dataList = new ArrayList<List<Object>>();

    report.stream().forEach(c -> {
      List<Object> data = new ArrayList<Object>();
      data.add(c.getReportdate());
      data.add(c.getAmount());
      data.add(c.getNum());
      dataList.add(data);
    });
    return dataList;
  }
}
