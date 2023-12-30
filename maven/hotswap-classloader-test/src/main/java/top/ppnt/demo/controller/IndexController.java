package top.ppnt.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ping E Lee
 *
 */
@RestController
@RequestMapping("")
public class IndexController {

  @RequestMapping("")
  public String index() {
    return this.getClass().getClassLoader().toString();
  }
  
  @RequestMapping("1")
  public String onlyOne() {
    return "7";
  }
}
