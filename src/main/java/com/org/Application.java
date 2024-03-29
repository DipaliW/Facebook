package com.org;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.org.util.PropertyManager;
import com.org.util.Language;

public class Application {

  public static void init() {

    PropertyManager manager = PropertyManager.getInstance();

    String browsers = manager.getProperty("browser");
    String[] browser = browsers.split(",");

    String language = manager.getProperty("language");
    if (language.isEmpty()) {
      System.out.println("No language preference has been setup");
      return;
    } else {
      Language.setLanguage(language);
    }
    // Create an instance on TestNG
    TestNG testNG = new TestNG();
    List<XmlTest> tests = new ArrayList<XmlTest>();
    // Create an instance of XML Suite and assign a name for it.
    XmlSuite suite = new XmlSuite();
    List<String> listeners = new ArrayList<String>();
    listeners.add("com.org.util.ReportListener");
    suite.setListeners(listeners);
    for (String browserInstance : browser) {
      // Create an instance of XmlTest and assign a name for it.
      XmlTest test = new XmlTest(suite);
      test.setName(browserInstance);

      // Add any parameters that you want to set to the Test.
      Map params = new HashMap();
      params.put("browser", browserInstance);
      test.setParameters(params);

      // Create a list which can contain the classes that you want to run.
      List<XmlClass> classes = new ArrayList<XmlClass>();
      classes.add(new XmlClass("com.org.test.Login"));

      // Assign that to the XmlTest Object created earlier.
      test.setXmlClasses(classes);

      // Create a list of XmlTests and add the Xmltest you created earlier to it.

      tests.add(test);
    }
    // add the list of tests to your Suite.
    suite.setTests(tests);

    // Add the suite to the list of suites.
    List<XmlSuite> suites = new ArrayList<XmlSuite>();
    suites.add(suite);

    // Set the list of Suites to the testNG object you created earlier.
    testNG.setXmlSuites(suites);

    // invoke run() - this will run your class.
    testNG.run();
  }

  public static void main(String args[]) {
    init();
  }
}
