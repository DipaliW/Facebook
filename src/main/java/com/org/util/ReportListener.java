package com.org.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

public class ReportListener implements IReporter {

  private ExtentReports extent;

  public void generateReport(
      List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    extent = new ExtentReports("output/" + ScreenshotUtility.getDateTime() + "/Report.html", true);

    for (ISuite suite : suites) {
      Map<String, ISuiteResult> result = suite.getResults();
      ExtentTest suiteTest = extent.startTest(suite.getName());
      for (ISuiteResult r : result.values()) {
        ITestContext context = r.getTestContext();
        buildTestNodes(suiteTest, context.getPassedTests(), LogStatus.PASS);
        buildTestNodes(suiteTest, context.getFailedTests(), LogStatus.FAIL);
        buildTestNodes(suiteTest, context.getSkippedTests(), LogStatus.SKIP);
      }
      extent.endTest(suiteTest);
    }

    extent.flush();
    extent.close();
  }

  private void buildTestNodes(ExtentTest suiteTest, IResultMap tests, LogStatus status) {
    ExtentTest test;

    if (tests.size() > 0) {
      for (ITestResult result : tests.getAllResults()) {
        ITestNGMethod method = result.getMethod();
        test =
            extent.startTest(
                method.getMethodName() + " [" + getMethodParams(result.getParameters()) + "]");

        test.getTest().setStartedTime(getTime(result.getStartMillis()));
        test.getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) test.assignCategory(group);

        String message = "Test " + status.toString().toLowerCase() + "ed";

        if (result.getThrowable() != null) message = result.getThrowable().getMessage();

        test.log(status, message);
        suiteTest.appendChild(test);
        extent.endTest(test);
      }
    }
  }

  private Date getTime(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
  }

  public String getMethodParams(Object[] params) {
    StringBuilder data = new StringBuilder();
    for (int index = 0; index < params.length; index++) {
      Object object = params[index];
      if (object instanceof String) {
        data.append((String) object);
        if (index != (params.length - 1)) {
          data.append(", ");
        }
      }
    }
    return data.toString();
  }
}
