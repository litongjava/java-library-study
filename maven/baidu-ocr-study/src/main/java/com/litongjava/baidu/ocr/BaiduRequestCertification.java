package com.litongjava.baidu.ocr;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class BaiduRequestCertification {

  private static final String filename = "E:\\document\\k.开发资料总结\\09.平台API\\appkey-and-appscret.xls";

  private static final String uuid = "dc347e286fde4767a497cbdda112509a";

  private static RequestCertification requestCertification;
  static {
    AnalysisEventListener<RequestCertification> analysisEventListener = new AnalysisEventListener<RequestCertification>() {

      public void invoke(RequestCertification data, AnalysisContext context) {
        if (uuid.equals(data.getId())) {
          requestCertification = data;
          return;
        }

      }

      public void doAfterAllAnalysed(AnalysisContext context) {
      }

    };

    EasyExcel.read(filename, RequestCertification.class, analysisEventListener).sheet().doRead();
  }

  public static String getClientId() {
    return requestCertification.getApikey();

  }

  public static String getClientSccert() {
    return requestCertification.getApisecret();
  }

  public static String getAppId() {
    return requestCertification.getAppid();
  }

}
