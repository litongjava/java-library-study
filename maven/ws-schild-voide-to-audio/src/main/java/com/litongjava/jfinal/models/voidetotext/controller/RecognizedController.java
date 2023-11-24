package com.litongjava.jfinal.models.voidetotext.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.models.voidetotext.model.Recognized;
import com.litongjava.jfinal.models.voidetotext.services.RecognizedService;
import com.litongjava.utils.vo.JsonBean;
import lombok.extern.slf4j.Slf4j;

@Path("recognized")
@Slf4j
public class RecognizedController extends Controller {
  @Inject
  private RecognizedService recognizedService;

  public void list() {
    List<Recognized> studentList = Recognized.dao.findAll();
    renderJson(studentList);
  }

  public void getById(int id) {
    log.info("id:{}", id);
    Recognized byId = Recognized.dao.findById(id);
    JsonBean<Recognized> jsonBean = new JsonBean<>();
    jsonBean.setData(byId);
    renderJson(jsonBean);
  }

  public void getTextById(int id) {
    log.info("id:{}", id);
    Recognized recognized = Recognized.dao.findByIdLoadColumns(id, "text");
    String text = recognized.getText();
    JSONObject jsonObject = JSON.parseObject(text);
    JSONArray jsonArray = jsonObject.getJSONArray("result");
    log.info("array size:{}", jsonArray.size());

    StringBuffer stringBuffer = new StringBuffer();
    jsonArray.forEach(e -> {
      stringBuffer.append(e + "\r\n");
    });
    renderJson(new JsonBean<Void>(stringBuffer.toString()));
  }

  /**
   * 返回数据总条数
   */
  public void count() {
    int total = Db.queryInt("select count(*) from recognized");
    JsonBean<Object> jsonBean = new JsonBean<>();
    jsonBean.setData(total);
    renderJson(jsonBean);
  }

  /**
   * 将文件名和文字导出为Excel
   */
  public void exportToExcel() {
    renderJson(new JsonBean<Void>(recognizedService.exportToExcel()));
  }
  
  public void exportMergeFilename() {
    renderJson(new JsonBean<Void>(recognizedService.exportMergeFilename()));
  }
}