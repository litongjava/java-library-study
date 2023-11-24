package com.litongjava.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.litongjava.utils.vo.JsonBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author litongjava
 * @date 2020年8月29日_下午5:45:26 
 * @version 1.0 
 * @desc
 * 全局异常烂机器
 */
@Slf4j
public class ExceptionInterceptor implements Interceptor {
  @Override
  public void intercept(Invocation invocation) {
    try {
      // 执行方法
      invocation.invoke();
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      Controller c = invocation.getController();
      c.renderJson(new JsonBean<Void>(-1,e.getMessage()));
    }
  }
}