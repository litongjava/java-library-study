import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author litong
 * @date 2018年8月11日_下午8:36:26 
 * @version 1.0 
 */
public class StanderLog {

  private static final Logger log = LoggerFactory.getLogger(StanderLog.class);

  public static void main(String[] args) {
    while (true) {
      log.info("我的名字是李通");
    }
  }
}
