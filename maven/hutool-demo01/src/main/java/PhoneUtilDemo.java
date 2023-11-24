import cn.hutool.core.util.PhoneUtil;

public class PhoneUtilDemo {

  public static void main(String[] args) {
    boolean mobile = PhoneUtil.isMobile("18028654615");
    System.out.println(mobile);
  }
}
