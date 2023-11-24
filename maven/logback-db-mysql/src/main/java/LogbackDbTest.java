import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogbackDbTest {

  public static void main(String[] args) {
    log.info("logback start");
    for (int i = 0; i < 3; i++) {
      log.info("info {}", i);
      log.debug("debug {}", i);
      log.error("error {}", i);

    }

    log.info("logback stop");
  }
}