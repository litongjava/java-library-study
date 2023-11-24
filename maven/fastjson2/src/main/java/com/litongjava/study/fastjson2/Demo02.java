package com.litongjava.study.fastjson2;

import java.util.HashMap;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.litongjava.study.fastjson2.model.Hero;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */

public class Demo02 {
  public static void main(String[] args) {
    test01();
  }

  private static void test01() {
    // @formatter:off
    String jsonString = "{" +
        "'张三': { 'age':10,'groupTag':'A','name':'张三','score':23 }," +
        "'李四': { 'age':11,'groupTag':'B','name':'李四','score':28 }" +
        "}";
    // @formatter:on
    TypeReference<HashMap<String, Hero>> tr = new TypeReference<HashMap<String, Hero>>() {
    };
    HashMap<String, Hero> map = JSON.parseObject(jsonString, tr);
    System.out.println(map);
  }
}
