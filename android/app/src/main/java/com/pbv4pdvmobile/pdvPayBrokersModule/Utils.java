package com.pbv4pdvmobile.pdvPayBrokersModule;

import android.util.Log;

public class Utils {
  private static String childName = "";

  public static void log(String method) {
    childName = Utils.class.getSimpleName() + ".";
    String trueLog = childName + method;
    Log.i("IPPITest", trueLog);
  }

  public static void logErr(String method, String errString) {
    childName = Utils.class.getSimpleName() + ".";
    String errorLog = childName + method + "   errorMessage：" + errString;
    Log.e("IPPITest", errorLog);
  }

  public static String abbreviate(String str, Integer range) {
    String currentStr = str;

    if(currentStr.length() < range) {
      return str;
    } else {
      return str.substring(0, range) + "...";
    }
  }
}
