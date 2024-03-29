package com.org.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

/** Created by nileshkumar_shegokar on 6/11/2017. */
public class DriverFactory {

  private static WebDriver driver = null;

  public static WebDriver getCurrentDriver() {
    return driver;
  }

  public static WebDriver getDriver(String browser) {

    switch (browser) {
      case "Chrome":
        {
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          break;
        }
      case "Firefox":
        {
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
          break;
        }
      case "Safari":
        {
          driver = new SafariDriver();
          break;
        }
      case "Opera":
        {
          WebDriverManager.operadriver().setup();
          driver = new OperaDriver();
          break;
        }
      case "Edge":
        {
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
          break;
        }
      case "Internet Explorer":
        {
          WebDriverManager.iedriver().setup();
          driver = new InternetExplorerDriver();
          break;
        }
      case "Html Unit Driver":
        {
          driver = new HtmlUnitDriver();
          break;
        }
    }
    driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    return driver;
  }
}
