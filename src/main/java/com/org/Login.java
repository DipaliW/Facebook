package com.org;

import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Parameters;
import static org.testng.Assert.fail;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import com.org.util.SnapShot;
import com.org.util.DriverFactory;

public class Login {

  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private Integer iterationCounter = 1;

  @BeforeTest
  @Parameters("browser")
  public void setUp(String browser) throws Exception {
    driver = DriverFactory.getDriver(browser);
    driver.manage().window().maximize();
  }

  @Test(
      dataProvider = "LoginDataProvider",
      dataProviderClass = com.org.data_provider.LoginDataProvider.class)
  public void test(String param1, String param2) {
    driver.get("http://www.facebook.com");
    driver
        .findElement(By.cssSelector(XPathProperties.getValue("Facebookloginorsignup.email")))
        .sendKeys(param1);
    driver
        .findElement(By.cssSelector(XPathProperties.getValue("Facebookloginorsignup.pass")))
        .sendKeys(param2);
    driver
        .findElement(By.cssSelector(XPathProperties.getValue("Facebookloginorsignup.logIn")))
        .click();
    Assert.assertEquals(
        driver
            .findElement(By.cssSelector(XPathProperties.getValue("LogintoFacebook.element")))
            .getText(),
        "The password that you've entered is incorrect. Forgotten password?");
  }

  @AfterMethod
  public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
    if (testResult.getStatus() == ITestResult.FAILURE) {
      System.out.println(testResult.getStatus());
      SnapShot.takeSnapShot(driver, "/error.png");
    }
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
