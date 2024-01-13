package com.pbv4pdvmobile.pdvPayBrokersModule.printer;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pax.dal.IDAL;
import com.pax.neptunelite.api.NeptuneLiteUser;

public class Printer extends Application {
  private static IDAL dal;
  private final Context context;

  public Printer(Context appContext) {
    this.context = appContext;
    dal = getDal();
  }

  public IDAL getDal(){
    if(dal == null){
      try {
        long start = System.currentTimeMillis();
        dal = NeptuneLiteUser.getInstance().getDal(context);
        Log.i("Test","get dal cost:"+(System.currentTimeMillis() - start)+" ms");
      } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(context, "error occurred,DAL is null.", Toast.LENGTH_LONG).show();
      }
    }
    return dal;
  }
}
