package com.litongjava.jfinal.models.voidetotext.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Data
public class Student implements Serializable {
  private Integer id;
  private String name;
  private Double salary;
  private Date birthday;
}