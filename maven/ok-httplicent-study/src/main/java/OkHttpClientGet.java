import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@Slf4j
public class OkHttpClientGet {
  public static void main(String[] args) {
    String url = "http://api.k780.com/?app=weather.history";
    okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
    OkHttpClient okHttpClient = new OkHttpClient();
    final Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {

      }

      @Override
      public void onResponse(Call call, okhttp3.Response response) throws IOException {

      }
    });

  }

}
