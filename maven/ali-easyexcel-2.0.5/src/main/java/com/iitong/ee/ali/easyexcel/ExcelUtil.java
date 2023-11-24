package com.iitong.ee.ali.easyexcel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

public class ExcelUtil {
  public static <T> void writeListTo(OutputStream os, List<T> data, Class<T> clazz, List<String> simpleHead) {

    WriteSheet writeSheet = new WriteSheet();
    writeSheet.setClazz(clazz);
    writeSheet.setNeedHead(true);

    List<List<String>> head = simpleHead.stream().map(a -> Arrays.asList(a)).collect(Collectors.toList());
    writeSheet.setHead(head);

    ExcelWriter write = EasyExcel.write(os).build();
    write.write(data, writeSheet);
    write.finish();
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> readListFrom(InputStream is, Class<T> clz) {
    SyncReadListener tmpListener = new SyncReadListener();
    ReadSheet readSheet = new ReadSheet();
    readSheet.setClazz(clz);

    ExcelReaderBuilder builder = EasyExcel.read(is);
    ExcelReader reader = builder.registerReadListener(tmpListener).build();
    reader.read(readSheet);

    return (List<T>) tmpListener.getList();
  }
}
