package com.org.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class XPathProperties {

  static Properties prop = null;

  public static String getValue(String name) {
    if (prop == null) {
      prop = new Properties();
      try {
        InputStream input =
            TestAssertProperties.class.getClassLoader().getResourceAsStream("paths.properties");
        System.out.println("Stream :" + input);
        prop.load(input);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return prop.getProperty(name);
  }
}
