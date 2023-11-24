import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter // 这里使用的注解，你也可以讲name和code生成get，set方法，set没啥用，删掉就行了
public enum AirlineTypeEnum {
  CHOOSE("请选择", ""), TOP_LINE("VIVO", "1"), POPULAR_LINE("OPP", "2"), ALL("全部", "3"),;

  // 声明一个构造方法
  AirlineTypeEnum(String name, String code) {
    this.name = name;
    this.code = code;
  }

  private String name;
  private String code;

  // 讲枚举转换成list格式，这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法，你就可以得到code 和name了
  public static List toList() {
    List list = new ArrayList();
    // 一样, 唯一它帮你做的(其实是javac帮你做的), 就是自动推导(不是"倒")尖括号里的数据类型.
    for (AirlineTypeEnum airlineTypeEnum : AirlineTypeEnum.values()) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("code", airlineTypeEnum.getCode());
      map.put("name", airlineTypeEnum.getName());
      list.add(map);
    }
    return list;
  }

}