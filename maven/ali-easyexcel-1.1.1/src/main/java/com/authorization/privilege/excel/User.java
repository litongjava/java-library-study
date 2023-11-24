package com.authorization.privilege.excel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String uid;
  private String name;
  private Integer age;
  private Date birthday;
}