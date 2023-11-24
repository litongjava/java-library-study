package com.example.demo.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

public class MailUtil {
  // 邮箱
  private static String mailServerHost = "smtp.126.com";
  private static String mailSenderAddress = "litongjava_alarm@126.com";
  private static String mailSenderNick = "服务器警报";
  private static String mailSenderUsername = "litongjava_alarm@126.com";
  private static String mailSenderPassword = "g8NmXf6hcy9NexE";

  /**
   * 发送 邮件方法 (Html格式，支持附件)
   *
   * @return void
   */
  public static void sendEmail(MailBean mailInfo) {
    HtmlEmail email = new HtmlEmail();
    // 配置信息
    email.setHostName(mailServerHost);
    try {
      email.setFrom(mailSenderAddress, mailSenderNick);
    } catch (EmailException e) {
      e.printStackTrace();
    }
    email.setAuthentication(mailSenderUsername, mailSenderPassword);
    email.setCharset("UTF-8");
    email.setSubject(mailInfo.getSubject());
    try {
      email.setHtmlMsg(mailInfo.getContent());
    } catch (EmailException e) {
      e.printStackTrace();
    }

    // 添加附件
    List<EmailAttachment> attachments = mailInfo.getAttachments();
    if (null != attachments && attachments.size() > 0) {
      for (int i = 0; i < attachments.size(); i++) {
        try {
          email.attach(attachments.get(i));
        } catch (EmailException e) {
          e.printStackTrace();
        }
      }
    }

    // 收件人
    List<String> toAddress = mailInfo.getTo();
    if (null != toAddress && toAddress.size() > 0) {
      for (int i = 0; i < toAddress.size(); i++) {
        try {
          email.addTo(toAddress.get(i));
        } catch (EmailException e) {
          e.printStackTrace();
        }
      }
    }
    // 抄送人
    List<String> ccAddress = mailInfo.getCc();
    if (null != ccAddress && ccAddress.size() > 0) {
      for (int i = 0; i < ccAddress.size(); i++) {
        try {
          email.addCc(ccAddress.get(i));
        } catch (EmailException e) {
          e.printStackTrace();
        }
      }
    }
    // 邮件模板 密送人
    List<String> bccAddress = mailInfo.getBcc();
    if (null != bccAddress && bccAddress.size() > 0) {
      for (int i = 0; i < bccAddress.size(); i++) {
        try {
          email.addBcc(ccAddress.get(i));
        } catch (EmailException e) {
          e.printStackTrace();
        }
      }
    }
    try {
      email.send();
    } catch (EmailException e) {
      e.printStackTrace();
    }
  }
}