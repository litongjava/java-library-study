package com.litongjava.study.fastjson2.model;

import lombok.Data;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@Data
public class Hero {
  //{ 'age':10,'groupTag':'A','name':'张三','score':23 }
  private int age;
  private String groupTag;
  private String name;
  private int score;

}
