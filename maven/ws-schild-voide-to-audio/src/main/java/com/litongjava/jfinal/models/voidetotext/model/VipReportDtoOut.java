package com.litongjava.jfinal.models.voidetotext.model;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@HeadRowHeight(20) // 表头行高
@ColumnWidth(15) // 表头行宽
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VipReportDtoOut {
  @ExcelProperty(value = "日期", index = 0)
  private String reportdate;
  @ExcelProperty(value = "包月金额", index = 1)
  private BigDecimal amount;
  @ExcelProperty(value = "包月数量", index = 2)
  private Long num;
}
