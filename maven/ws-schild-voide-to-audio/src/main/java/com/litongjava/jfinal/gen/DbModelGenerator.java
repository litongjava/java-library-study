package com.litongjava.jfinal.gen;

import com.litongjava.jfinal.db.gen.JFinalGenerator;

public class DbModelGenerator {
  public static void main(String[] args) {
    String modelPackageName = "com.litongjava.jfinal.voidetotext.model";
    JFinalGenerator.generate(modelPackageName);
  }
}