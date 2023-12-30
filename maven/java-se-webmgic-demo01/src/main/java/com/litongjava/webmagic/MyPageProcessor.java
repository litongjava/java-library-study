package com.litongjava.webmagic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.Spider;

public class MyPageProcessor implements PageProcessor {

  // 可以对爬虫进行一些配置
  private Site site = Site.me();


  public Site getSite() {
    return site;
  }

  public void process(Page page) {
    Html html = page.getHtml();
    Selectable css = html.css("div#news_div ul li a", "text");
    List<String> all = css.all();
    // 把数据交给Pipeline进行输出
    page.putField("content", all);
  }

  // WebMagic使用的默认下载器是HttpClient
  public static void main(String[] args) {

    // 提供自己定义的PageProcessor
    String url = "https://www.jd.com/moreSubject.aspx";
    Spider.create(new MyPageProcessor())
        // 设置初始下载url地址
        .addUrl(url).run();

  }
}