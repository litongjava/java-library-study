package top.ppnt.tencent.cloud.tanslate;

import java.util.List;
import java.util.Map;

import com.litongjava.utils.excel.PoiExcelUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;

public class TextTranslate {
  public static void main(String[] args) {
    // 读取excel获取id和key
    String secretId = "SecretId";
    String secretKey = "SecretKey";
    String docPath = "D:\\document\\secret\\appkey-and-appscret.xls";
    List<Map<String, Object>> listMap = PoiExcelUtils.readExcel(docPath, 0);
    for (Map<String, Object> map : listMap) {
      if ("12d28291e4314d48bcdd8a7566ba5337".equals(map.get("id"))) {
        secretId = (String) map.get("appkey");
        secretKey = (String) map.get("apisecret");
      }
    }
    try {
      // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
      // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
      Credential cred = new Credential(secretId, secretKey);
      // 实例化一个http选项，可选的，没有特殊需求可以跳过
      HttpProfile httpProfile = new HttpProfile();
      httpProfile.setEndpoint("tmt.tencentcloudapi.com");
      // 实例化一个client选项，可选的，没有特殊需求可以跳过
      ClientProfile clientProfile = new ClientProfile();
      clientProfile.setHttpProfile(httpProfile);
      // 实例化要请求产品的client对象,clientProfile是可选的
      TmtClient client = new TmtClient(cred, "ap-beijing", clientProfile);
      // 实例化一个请求对象,每个接口都会对应一个request对象
      TextTranslateRequest req = new TextTranslateRequest();
      req.setSourceText("How are you");
      req.setSource("en");
      req.setTarget("zh");
      req.setProjectId(0L);
      // 返回的resp是一个TextTranslateResponse的实例，与请求对象对应
      TextTranslateResponse resp = client.TextTranslate(req);
      // 输出json格式的字符串回包
      System.out.println(TextTranslateResponse.toJsonString(resp));
    } catch (TencentCloudSDKException e) {
      System.out.println(e.toString());
    }
  }
}