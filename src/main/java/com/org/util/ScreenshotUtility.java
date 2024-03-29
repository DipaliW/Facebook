package com.org.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScreenshotUtility {

  private static String DATE_TIME = null;

  public static void updateDateTIme() {
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("ddMyyyy hhmmss");
    DATE_TIME = formatter.format(date);
  }

  public static String getDateTime() {
    if (DATE_TIME == null) {
      updateDateTIme();
    }
    return DATE_TIME;
  }
}
