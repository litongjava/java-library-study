package com.example.demo.email;

import lombok.Data;
import org.apache.commons.mail.EmailAttachment;

import java.util.List;

@Data
public class MailBean {
  // 收件人
  private List<String> to;
  // 抄送人地址
  private List<String> cc;
  // 密送人
  private List<String> bcc;
  // 附件信息
  private List<EmailAttachment> attachments = null;
  // 邮件主题
  private String subject;
  // 邮件的文本内容
  private String content;
}