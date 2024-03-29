package com.org.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class SnapShot {

  public static boolean takeSnapShot(WebDriver webdriver, String fileWithPath) {

    try {
      // Convert web driver object to TakeScreenshot

      TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

      // Call getScreenshotAs method to create image file

      File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

      // Move image file to new destination
      File location = new File("output/" + ScreenshotUtility.getDateTime() + "/snapshot/");
      if (!location.exists()) {
        location.mkdirs();
      }
      File destFile = new File(location.getAbsolutePath() + fileWithPath);
      System.out.println("Folder Structure :" + destFile.exists());
      System.out.println("Folder Structure :" + destFile.getAbsolutePath().toString());
      // Copy file at destination

      FileUtils.copyFile(srcFile, destFile);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
