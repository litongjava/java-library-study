package com.example.demo.email;

import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    MailBean mailInfo = new MailBean();
    List<String> toList = new ArrayList<String>();
    toList.add("litongjava@qq.com");
    mailInfo.setTo(toList);// 收件人

    List<String> ccList = new ArrayList<String>();
    ccList.add("litongjava_alarm@126.com");
    mailInfo.setCc(ccList);// 抄送人
    mailInfo.setSubject("数据库连接失败");
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("<h5>数据库连接失败</h5>");
    stringBuffer.append("详情...");
    mailInfo.setContent(stringBuffer.toString());
    MailUtil.sendEmail(mailInfo);
  }
}