import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class UrlEncodeTest {

  @Test
  public void encodeTest() throws UnsupportedEncodingException {
    String originText="人之初,性本善";
    originText = URLEncoder.encode(originText, "utf-8");
    System.out.println(originText);
  }
}
