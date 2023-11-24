package com.uairobot.bill.study.config;

import java.net.URL;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigurationsDemo01 {
  public static void main(String[] args) {
    Configurations configurations = new Configurations();
    URL resource = CommonsConfigUtil.class.getClassLoader().getResource("config.properties");
    PropertiesConfiguration properties = null;
    try {
      properties = configurations.properties(resource.getFile());
    } catch (ConfigurationException e) {
      e.printStackTrace();
    }
    String string = properties.getString("httpResponseEncoding");
    System.out.println(string);
  }
}
