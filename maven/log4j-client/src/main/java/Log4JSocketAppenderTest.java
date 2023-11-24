import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author litong
 * @date 2018年8月11日_下午7:56:08 
 * @version 1.0 
 */
public class Log4JSocketAppenderTest {
  public static void main(String[] args) throws InterruptedException {
    //1.得到配置文件的url
    URL resource = Log4JSocketAppenderTest.class.getResource("/log4j.xml");
    System.out.println("resource:"+resource);
    //2.加载配置文件
    DOMConfigurator.configure(resource);
    //3.获取remote记录器
    Logger logger = LogManager.getLogger("remote");
    //4.写入日志
    while(true) {
      logger.info("打印日志到远程文件系统");
      Thread.sleep(3);
    }
    
  }
}
