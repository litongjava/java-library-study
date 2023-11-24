import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Test {

  @Test
  public void log() {
    Logger log = LoggerFactory.getLogger(this.getClass());
    log.info("你好");
  }
}
