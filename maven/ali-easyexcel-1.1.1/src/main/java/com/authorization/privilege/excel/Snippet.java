package com.authorization.privilege.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

public class Snippet {
  private SysSystemReadMapper sysSystemReadMapper;

  public ResultVO<Void> exportSysSystemExcel(SysSystemVO sysSystemVO, HttpServletResponse response) throws Exception {
  
      ServletOutputStream out = null;
      try {
          out = response.getOutputStream();
          ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
  
          // 设置EXCEL名称
          String fileName = new String(("SystemExcel").getBytes(), "UTF-8");
  
          // 设置SHEET名称
          Sheet sheet = new Sheet(1, 0);
          sheet.setSheetName("系统列表sheet1");
  
          // 设置标题
          Table table = new Table(1);
          List<List<String>> titles = new ArrayList<List<String>>();
          titles.add(Arrays.asList("系统名称"));
          titles.add(Arrays.asList("系统标识"));
          titles.add(Arrays.asList("描述"));
          titles.add(Arrays.asList("状态"));
          titles.add(Arrays.asList("创建人"));
          titles.add(Arrays.asList("创建时间"));
          table.setHead(titles);
  
          // 查数据写EXCEL
          List<List<String>> dataList = new ArrayList<>();
          List<SysSystemVO> sysSystemVOList = this.sysSystemReadMapper.selectSysSystemVOList(sysSystemVO);
          if (!CollectionUtils.isEmpty(sysSystemVOList)) {
              sysSystemVOList.forEach(eachSysSystemVO -> {
//                  dataList.add(Arrays.asList(
//                          eachSysSystemVO.getSystemName(),
//                          eachSysSystemVO.getSystemKey(),
//                          eachSysSystemVO.getDescription(),
//                          eachSysSystemVO.getState().toString(),
//                          eachSysSystemVO.getCreateUid(),
//                          eachSysSystemVO.getCreateTime().toString()
//                  ));
              });
          }
          writer.write0(dataList, sheet, table);
  
          // 下载EXCEL
          response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("gb2312"), "ISO-8859-1") + ".xls");
          response.setContentType("multipart/form-data");
          response.setCharacterEncoding("utf-8");
          writer.finish();
          out.flush();
  
      } finally {
          if (out != null) {
              try {
                  out.close();
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }
  
      return ResultVO.getSuccess("导出系统列表EXCEL成功");
  }
}

