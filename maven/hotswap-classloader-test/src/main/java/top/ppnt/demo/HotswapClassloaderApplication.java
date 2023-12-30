package top.ppnt.demo;
/**
 * @author Ping E Lee
 *
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;

@SpringBootApplication
public class HotswapClassloaderApplication {
  public static void main(String[] args) {
    //Diagnostic.setDebug(true);
    SpringApplicationWrapper.run(HotswapClassloaderApplication.class, args,true);
    //SpringApplication.run(HotswapClassloaderApplication.class, args);
  }
}
