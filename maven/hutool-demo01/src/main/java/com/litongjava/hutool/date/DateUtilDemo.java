package com.litongjava.hutool.date;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class DateUtilDemo {
  public static void main(String[] args) {
    String dateStr = "2017-03-01";
    Date date = DateUtil.parse(dateStr);
    System.out.println(date);
    
    dateStr = "2023-01-06 12:04:40";
    date = DateUtil.parse(dateStr);
    System.out.println(date);
    
  }

}
