package top.ppnt.arcsoft.face;

import java.util.List;
import java.util.Map;

import com.litongjava.utils.excel.PoiExcelUtils;

public class AppidAndSdkKey {
  public static void main(String[] args) {
    String appId = null;
    String sdkKey = null;
    String docPath = "D:\\document\\secret\\appkey-and-appscret.xls";
    List<Map<String, Object>> listMap = PoiExcelUtils.readExcel(docPath, 0);
    for (Map<String, Object> map : listMap) {
      if ("12d28291e4314d48bcdd8a7566ba5337".equals(map.get("id"))) {
        appId = (String) map.get("appid");
        sdkKey = (String) map.get("apisecret");
      }
    }
    System.out.println(appId + " " + sdkKey);
  }
}
