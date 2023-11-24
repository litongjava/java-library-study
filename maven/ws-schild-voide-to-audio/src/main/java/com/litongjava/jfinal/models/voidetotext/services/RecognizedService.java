package com.litongjava.jfinal.models.voidetotext.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.litongjava.jfinal.models.voidetotext.model.Recognized;
import com.litongjava.jfinal.models.voidetotext.model.RecognizedDto;
import com.litongjava.utils.string.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by litonglinux@qq.com on 12/1/2021_8:17 PM
 */
@Slf4j
public class RecognizedService {
  public String exportToExcel() {
    // 查询出所有数据
    List<Recognized> recognizeds = Recognized.dao.findAll();
    List<RecognizedDto> writeList = new ArrayList<>();
    recognizeds.forEach(e -> {
      JSONObject jsonObject = JSON.parseObject(e.getText());
      // log.info("jsonObject:{}",jsonObject);
      String err_msg = jsonObject.getString("err_msg");
      if ("success.".equals(err_msg)) {
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        log.info("array size:{}", jsonArray.size());

        StringBuffer stringBuffer = new StringBuffer();
        jsonArray.forEach(r -> {
          stringBuffer.append(r + "\r\n");
        });
        writeList.add(new RecognizedDto(e.getPcmFile(), stringBuffer.toString()));

      } else {
        log.info("pcmFile:{},err_msg:{}", e.getPcmFile(), err_msg);
      }
    });
//    map.forEach((k,v)->{
//      //将k,v写入excel
//
//    });
    // EasyExcel.write("学生信息表.xlsx", Student.class).sheet().doWrite(getData());
    File file = new File("转写信息表.xlsx");
    EasyExcel.write(file).sheet().doWrite(writeList);
    String filePath = file.getAbsolutePath();
    log.info("写入文件:{}", filePath);

    return filePath;
  }

  public String exportMergeFilename() {
    List<Recognized> recognizeds = Recognized.dao.findAll();
    
    List<RecognizedDto> writeList=new ArrayList<>();
    
    StringBuffer textStringBuffer = new StringBuffer();
    String lastRealFilename="";
    String lastPcmFile=null;
    for (Recognized recognized : recognizeds) {
      JSONObject jsonObject = JSON.parseObject(recognized.getText());
      //log.info("jsonObject:{}",jsonObject);
      String err_msg = jsonObject.getString("err_msg");
      if("success.".equals(err_msg)){
        JSONArray jsonArray = jsonObject.getJSONArray("result");

        StringBuffer stringBuffer = new StringBuffer();
        jsonArray.forEach(r -> {
          stringBuffer.append(r + "\r\n");
        });
        String pcmFile = recognized.getPcmFile();
        String realFilename = getRealFilename(pcmFile);
        if(lastRealFilename.equals(realFilename)) {
          textStringBuffer.append(stringBuffer.toString());
        }else {
          if(StringUtils.isEmpty(lastPcmFile)) {
            lastPcmFile=pcmFile;
          }else {
            log.info("add:{}",lastRealFilename);
            writeList.add(new RecognizedDto(lastRealFilename,textStringBuffer.toString()));
          }
          lastRealFilename=realFilename;
          textStringBuffer.setLength(0);
          textStringBuffer.append(stringBuffer.toString());
        }
        
      }else{
        log.info("pcmFile:{},err_msg:{}",recognized.getPcmFile(),err_msg);
      }
      
    }
    File file = new File("转写信息表-merge.xlsx");
    EasyExcel.write(file).sheet().doWrite(writeList);
    String filePath = file.getAbsolutePath();
    log.info("写入文件:{}", filePath);
    return file.getAbsolutePath();
  }

  public String getRealFilename(String filePath) {
    String[] split = filePath.split("\\\\");
    String filenameWithChunk = split[4];
    log.info("filenameWithChunk:{}", filenameWithChunk);
    split = filenameWithChunk.split("_");
    String realFilename = split[0];
    log.info("realFilename:{}", realFilename);
    return realFilename;
  }
}
