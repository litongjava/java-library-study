package com.cloud.data.entity.dto;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日结算账单导入类
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class OutDbillDto extends BaseRowModel implements Serializable {
  @ExcelProperty(value = "机组调度名称（必填）", index = 0)
  private String unitDispatchName;
  @ExcelProperty(value = "日期（必填）", index = 1)
  private String billTime;
  @ExcelProperty(value = "收费项目", index = 2)
  private String project;
  @ExcelProperty(value = "本期电量（万kWh）", index = 3)
  private String quan;
  @ExcelProperty(value = "单价（厘/kWh）", index = 4)
  private String avgPrice;
  @ExcelProperty(value = "本期电费（元）", index = 5)
  private String price;
  /**标记*/
  private String mark;
  /**错误信息*/
  private String errMsg;
}