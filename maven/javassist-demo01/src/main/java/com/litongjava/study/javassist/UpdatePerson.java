package com.litongjava.study.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.reflect.Method;

public class UpdatePerson {

  public static void update() throws Exception {
    String classFullName = "com.litongjava.study.javassist.PersonService";
    ClassPool pool = ClassPool.getDefault();

    CtClass cc = pool.get(classFullName);

    CtMethod personFly = cc.getDeclaredMethod("personFly");
    personFly.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
    personFly.insertAfter("System.out.println(\"成功落地。。。。\");");

    // 新增一个方法
    CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[] {}, cc);
    ctMethod.setModifiers(Modifier.PUBLIC);
    ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
    cc.addMethod(ctMethod);

    Object person = cc.toClass().newInstance();
    // 调用 personFly 方法
    Method personFlyMethod = person.getClass().getMethod("personFly");
    personFlyMethod.invoke(person);
    // 调用 joinFriend 方法
    Method execute = person.getClass().getMethod("joinFriend");
    execute.invoke(person);
  }

  public static void main(String[] args) {
    String str="C:\\Users\\Administrator\\AppData\\Local\\Packages\\36699Atelier39.forWin10_pke1vz55rvc1r\\LocalCache\\BilibiliDownload\\78180092";
    try {
      update();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}