package com.uairobot.bill.fastjson.dataobject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class WirteToJson {
  public static void main(String[] args) {

    DbUserPaswd dbUserPaswd = new DbUserPaswd();
    dbUserPaswd.setUrl("jdbc:oracle:thin:@47.93.60.245:1521/orcl");
    dbUserPaswd.setUser("wise_heads");
    dbUserPaswd.setPswd("wise_heads");
    dbUserPaswd.setMsg("连接数据库失败${url},${user},${pswd}");
    List<String> emails = new ArrayList<>();
    emails.add("litongjava@qq.com");
    dbUserPaswd.setEmails(emails);

    List<DbUserPaswd> list = new ArrayList<>();
    list.add(dbUserPaswd);
    String configFileName = "config.json";
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(configFileName);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      // 写入成功后户自动关流
      JSON.writeJSONString(bufferedWriter, list);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
