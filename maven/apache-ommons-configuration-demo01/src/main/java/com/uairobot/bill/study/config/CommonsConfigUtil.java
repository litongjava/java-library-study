package com.uairobot.bill.study.config;

import java.net.URL;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class CommonsConfigUtil {
  private static PropertiesConfiguration properties = null;

  public static String get(String key) {
    PropertiesConfiguration properties = getPropertiesConfiguration();
    return properties.getString(key);

  }

  private static PropertiesConfiguration getPropertiesConfiguration() {
    if (properties == null) {
      Configurations configurations = new Configurations();
      URL resource = CommonsConfigUtil.class.getClassLoader().getResource("config.properties");

      try {
        properties = configurations.properties(resource.getFile());
      } catch (ConfigurationException e) {
        e.printStackTrace();
      }
    }
    return properties;
  }
}
