package com.liotngjava.luaj.demo01;

import java.net.URL;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.litongjava.utils.reflection.ClassPathUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaCallLua {
  public static void main(String[] args) {
    demo04();
  }

  private static void demo04() {
    Globals globals = JsePlatform.standardGlobals();
    URL url = ClassPathUtils.getResourceWithClassLoader("lua/demo03.lua");
    String luaPath = url.getFile();
    globals.loadfile(luaPath).call();
  }
  
  private static void demo03() {
    Globals globals = JsePlatform.standardGlobals();
    URL url = ClassPathUtils.getResourceWithClassLoader("lua/demo02.lua");
    String luaPath = url.getFile();
    globals.loadfile(luaPath).call();
  }
  private static void demo02() {
    // lua脚本文件所在路径
    // String luaPath = "src/main/resources/lua/login.lua";

    // 使用工具类获取路径
    URL url = ClassPathUtils.getResourceWithClassLoader("lua/login.lua");
    String luaPath = url.getFile();
    // 获取到的是全路径
    // log.info("luaPath:{}", luaPath);

    // JsePlatform
    Globals globals = JsePlatform.standardGlobals();
    // 加载脚本文件login.lua，并编译
    globals.loadfile(luaPath).call();
    // 获取无参函数hello
    LuaValue func = globals.get(LuaValue.valueOf("hello"));
    // 执行hello方法
    func.call();

    // 获取带参函数test
    LuaValue func1 = globals.get(LuaValue.valueOf("test"));
    // 执行test方法,传入String类型的参数参数
    LuaString arg1 = LuaValue.valueOf("I'am from Java!");
    LuaValue return1 = func1.call(arg1);
    String data = return1.toString();
    // 打印lua函数回传的数据
    log.info("data return from lua is:" + data);
  }

  private static void demo01() {
    String luaStr = "print 'hello,world!'";
    Globals globals = JsePlatform.standardGlobals();
    LuaValue chunk = globals.load(luaStr);
    chunk.call();
  }

}
