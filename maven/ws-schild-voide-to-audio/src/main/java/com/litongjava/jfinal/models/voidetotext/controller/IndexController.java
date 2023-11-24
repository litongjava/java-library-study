package com.litongjava.jfinal.models.voidetotext.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * Created by litonglinux@qq.com on 12/1/2021_7:44 PM
 */
@Path("/")
public class IndexController extends Controller {
  public void index(){
    renderText("");
  }
}
