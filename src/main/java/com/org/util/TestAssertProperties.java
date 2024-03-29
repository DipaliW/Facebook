package com.org.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestAssertProperties {

  static Properties prop = null;

  public static String getValue(String name) {
    if (prop == null) {
      prop = new Properties();
      try {
        InputStream input =
            TestAssertProperties.class
                .getClassLoader()
                .getResourceAsStream("assert_" + Language.language + ".properties");
        System.out.println("Stream :" + input);
        prop.load(input);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return prop.getProperty(name);
  }
}
