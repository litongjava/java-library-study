package com.uairobot.bill.fastjson.dataobject;

import java.util.List;

import lombok.Data;

@Data
public class DbUserPaswd {
  private String url, user, pswd, msg;
  private List<String> emails;
}
