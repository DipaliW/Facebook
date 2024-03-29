package com.org.util;

import java.util.Iterator;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WindowUtility {

  public static void switchWindow(WebDriver driver, String url) {

    Iterator<String> windows = driver.getWindowHandles().iterator();

    while (windows.hasNext()) {

      String child_window = windows.next();
      if (driver.switchTo().window(child_window).getCurrentUrl().equalsIgnoreCase(url)) {
        driver.switchTo().window(child_window);
        break;
      }
    }
  }

  public static void closeWindow(WebDriver driver, String url) {

    Iterator<String> windows = driver.getWindowHandles().iterator();

    while (windows.hasNext()) {

      String child_window = windows.next();
      if (driver.switchTo().window(child_window).getCurrentUrl().equalsIgnoreCase(url)) {
        driver.switchTo().window(child_window).close();
        break;
      }
    }
  }

  public static String getBrowserAndVersion() {
    RemoteWebDriver browserDriver = (RemoteWebDriver) DriverFactory.getCurrentDriver();
    if (browserDriver == null) {
      return "";
    }
    String browser_version = null;
    Capabilities cap = browserDriver.getCapabilities();
    String browsername = cap.getBrowserName();
    // This block to find out IE Version number
    if ("internet explorer".equalsIgnoreCase(browsername)) {
      String uAgent =
          (String)
              ((JavascriptExecutor) browserDriver).executeScript("return navigator.userAgent;");
      // uAgent return as "MSIE 8.0 Windows" for IE8
      if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
        browser_version =
            uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf("Windows") - 2);
      } else if (uAgent.contains("Trident/7.0")) {
        browser_version = "11.0";
      } else {
        browser_version = "0.0";
      }
    } else {
      // Browser version for Firefox and Chrome
      browser_version = cap.getVersion(); // .split(".")[0];
    }
    String browserversion = browser_version.substring(0, browser_version.indexOf("."));
    return browsername + " " + browserversion;
  }

  public static String OSDetector() {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")) {
      return "Windows";
    } else if (os.contains("nux") || os.contains("nix")) {
      return "Linux";
    } else if (os.contains("mac")) {
      return "Mac";
    } else if (os.contains("sunos")) {
      return "Solaris";
    } else {
      return "Other";
    }
  }
}
