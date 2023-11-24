import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class BiaoBeiRequestCertification {

  private static final String filename = "E:\\document\\k.开发资料总结\\09.平台API\\appkey-and-appscret.xls";

  private static final String uuid = "9526ddc7173c4e958208637d9dd0c426";

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

}
