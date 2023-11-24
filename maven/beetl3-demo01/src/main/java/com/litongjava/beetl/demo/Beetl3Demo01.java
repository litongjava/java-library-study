package com.litongjava.beetl.demo;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

public class Beetl3Demo01 {
  public static void main(String[] args) throws IOException {
    // 初始化代码
    StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
    Configuration cfg = Configuration.defaultConfiguration();
    GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
    // 获取模板
    Template t = gt.getTemplate("hello,${name}");
    //测试使用新的占位符
//    Template t = gt.getTemplate("hello,#{name}");
    // 绑定数据
    t.binding("name", "beetl");
    // 渲染结果
    String str = t.render();
    System.out.println(str);
  }
}
