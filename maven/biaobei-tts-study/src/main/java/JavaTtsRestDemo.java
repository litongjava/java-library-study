import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class JavaTtsRestDemo {

  /**
   * 合成使用的地址信息，rate、language等参数在本demo固定，开发者如需调整，参考https://www.data-baker.com/specs/file/tts_api_restful
   */
  public static String ttsUrl = "https://openapi.data-baker.com/tts?access_token=%s&domain=1&rate=2&audiotype=%s&language=zh"
      + "&voice_name=%s&speed=%s&volume=%s&text=%s";
  /**
   * 获取token的地址信息
   */
  public static String tokenUrl = "https://openapi.data-baker.com/oauth/2.0/token?grant_type=client_credentials&client_secret=%s&client_id=%s";
  public static String clientId = BiaoBeiRequestCertification.getClientId();
  public static String clientSecret = BiaoBeiRequestCertification.getClientSccert();

  /**
   * 仅作为demo示例 失败重试、token过期重新获取、日志打印等优化工作需要开发者自行完成
   **/
  public static void main(String[] args) {
    String accessToken = getAccessToken();
    log.info("accessToken:{}", accessToken);
    if (StringUtils.isNotEmpty(accessToken)) {
      //@formatter:off
      String text="墙角数枝梅\r\n" + 
          "凌寒独自开\r\n" + 
          "遥知不是雪\r\n" + 
          "唯有暗香来";
      //@formatter:on
      String md5Hex = DigestUtils.md5Hex(text);
      VoiceName[] values = VoiceName.values();
      for (VoiceName voiceName : values) {
        String filepath = "tts/"+voiceName + "_" + md5Hex + ".wav";
        log.info("filepath:{}", filepath);
        doSynthesis(accessToken, voiceName.toString(), text, 6, 5.0, 5.0, filepath);
        //防止qps limit
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }

  public static void doSynthesis(String accessToken, String voiceName, String originText, Integer audioType,
      Double speed, Double volume, String filePath) {
    // 在非浏览器上操作，需要把合成文本转化为utf-8格式
    try {
      originText = URLEncoder.encode(originText, "utf-8");
      String synthesisUrl = String.format(ttsUrl, accessToken, audioType, voiceName, speed, volume, originText);
      fetchTtsResponse(synthesisUrl, filePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 请求并获取音频流保存至本地文件：这里filePath为全路径
   *
   * @param url
   * @param filePath
   * @throws IOException
   */
  public static void fetchTtsResponse(String url, String filePath) throws IOException {
    OkHttpClient client = new OkHttpClient();
    // request 默认是get请求
    Request request = new Request.Builder().url(url).build();
    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String contentType = response.body().contentType().toString();
        if (response.body() != null && contentType.startsWith("audio")) {
          // 写入文件
          File targetFile = new File(filePath);
          Files.write(targetFile.toPath(), response.body().bytes());
        } else {
          log.info("没有写入文件,请debug调试");
          log.info("contentType:{},body:{}", contentType, response.body().string());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getAccessToken() {
    String accessToken = "";
    OkHttpClient client = new OkHttpClient();
    // request 默认是get请求
    String url = String.format(tokenUrl, clientSecret, clientId);
    Request request = new Request.Builder().url(url).build();
    JSONObject jsonObject;
    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        // 解析
        String resultJson = response.body().string();
        jsonObject = JSON.parseObject(resultJson);
        accessToken = jsonObject.getString("access_token");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return accessToken;
  }
}
