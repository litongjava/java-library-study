package com.litongjava.jfinal.models.voidetotext.model;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadRowHeight(20) // 表头行高
@ColumnWidth(50) // 表头行宽
public class RecognizedDto {
  private String pcmFile;
  private String text;
}
