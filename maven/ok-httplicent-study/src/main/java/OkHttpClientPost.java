import java.io.IOException;
import java.util.Base64.Decoder;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Slf4j
public class OkHttpClientPost {
  // json传输方式
  private final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  // 获取okHttpClient对象
  private final static OkHttpClient client =  new OkHttpClient();

  /**
   * post形式
   */
  public static String post(String url, String message) throws IOException {
    // 请求体传输json格式的数据
    RequestBody requestBody = RequestBody.create(JSON, message);
    // 创建请求
    Request request = new Request.Builder().url(url)
        // .header("User-Agent", "*****")
        // .addHeader("Accept", "*****")
        .post(requestBody).build();

    Call call = client.newCall(request);
    okhttp3.Response response = call.execute();

    if (response.isSuccessful()) {
      return response.body().string();
    } else {
      throw new IOException("Unexpected code " + response);
    }
  }

  /**
   * post形式  multipart
   */
  public static String postMultipartFormData(String url, Map<String, String> paramMap) throws IOException {

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    Set<String> keySet = paramMap.keySet();
    // 拼接请求参数
    for (String key : keySet) {
      if (key.equals("image_file")) {
        String image_file = paramMap.get("image_file");
        Decoder decoder = java.util.Base64.getDecoder();
        byte[] bytes = decoder.decode(image_file);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), bytes);
        builder.addFormDataPart("image_file", "image_file", fileBody);
      } else {
        builder.addFormDataPart(key, paramMap.get(key));
      }
    }
    MultipartBody body = builder.build();
    // 创建请求
    Request request = new Request.Builder().url(url).method("POST", body).addHeader("Content-Type", "multipart/form-data").build();

    Call call = client.newCall(request);
    okhttp3.Response response = call.execute();
    if (response.isSuccessful()) {
      return response.body().string();
    } else {

      int code = response.code();
      String message = response.message();
      log.error("OCR识别 响应状态码：{}  错误信息：{}", code, message);
      throw new IOException("Unexpected code " + response);
    }
  }

  /**
   * post形式
   */
  public static String postHeader(String url, String message, String header) throws IOException {
    // 请求体传输json格式的数据
    RequestBody requestBody = RequestBody.create(JSON, message);
    // 创建请求
    Request request = new Request.Builder().url(url).header("Authorization", header)
        // .addHeader("Accept", "*****")
        .post(requestBody).build();

    Call call = client.newCall(request);
    okhttp3.Response response = call.execute();

    if (response.isSuccessful()) {
      return response.body().string();
    } else {
      throw new IOException("Unexpected code " + response);
    }
  }

}