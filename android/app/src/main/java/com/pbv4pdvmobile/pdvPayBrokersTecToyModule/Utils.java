package com.pbv4pdvmobile.pdvPayBrokersTecToyModule;

import android.util.Log;

public class Utils {
  private static String childName = "";

  public static void log(String method) {
    childName = Utils.class.getSimpleName() + ".";
    String trueLog = childName + method;
    Log.i("TEC_TOY_LOG", trueLog);
  }

  public static void logErr(String method, String errString) {
    childName = Utils.class.getSimpleName() + ".";
    String errorLog = childName + method + "   errorMessage：" + errString;
    Log.e("TEC_TOY_LOG", errorLog);
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
