package top.ppnt.demo;
/**
 * @author Ping E Lee
 *
 */

import com.litongjava.hotswap.kit.HotSwapUtils;

public class ClassLoaderDemo {
  public static void main(String[] args) {
    // 获取一个类的classLoader,默认是sun.misc.Launcher$AppClassLoader@2a139a55
    ClassLoader classLoader = HotSwapUtils.class.getClassLoader();
    System.out.println(classLoader);

    //
    ClassLoader classLoader2 = HotSwapUtils.getClassLoader();
    System.out.println(classLoader2);
  }
}
