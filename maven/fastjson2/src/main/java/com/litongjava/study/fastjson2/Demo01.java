package com.litongjava.study.fastjson2;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
public class Demo01 {
  public static void main(String[] args) throws Exception {
    test02();
  }

  private static void test02() throws UnsupportedEncodingException {
    Product product = new Product();
    product.id = 1001;
    product.name = "DataWorks";

    // 将JavaBean对象生成JSON格式的对象字符串
    String jsonString = JSON.toJSONString(product);
    System.out.println(jsonString);
    // 将JavaBean对象生成JSON格式的数组字符串
    jsonString = JSON.toJSONString(product, JSONWriter.Feature.BeanToArray);
    System.out.println(jsonString);
    // 将JavaBean对象生成UTF8编码的byte[]
    byte[] utf8JSONBytes = JSON.toJSONBytes(product);
    System.out.println(new String(utf8JSONBytes, "UTF-8"));

    // 将JavaBean对象生成JSONB格式的byte[]
    byte[] jsonbBytes = JSONB.toBytes(product, JSONWriter.Feature.BeanToArray);
    System.out.println(new String(jsonbBytes, "UTF-8"));
  }

  private static void test01() {
    String strObject = "{\"id\":123}";
    JSONObject jsonObject = JSON.parseObject(strObject);
    int id = jsonObject.getIntValue("id");
    System.out.println(id);

    String strArray = "[\"id\", 123]";
    JSONArray jsonArray = JSON.parseArray(strArray);
    String name = jsonArray.getString(0);
    id = jsonArray.getIntValue(1);
    System.out.println(id);
  }
}
