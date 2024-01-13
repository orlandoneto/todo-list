package com.pbv4pdvmobile.pdvPayBrokersTecToyModule.printer;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import com.pbv4pdvmobile.pdvPayBrokersTecToyModule.Utils;

import br.com.itfast.tectoy.Dispositivo;
import br.com.itfast.tectoy.StatusImpressora;
import br.com.itfast.tectoy.TecToy;

public class TecToyPrinter extends Application {
  public static TecToy tectoy;

  public static TecToy init(String typeDevice, Context context) {
    switch (typeDevice) {
      case "T2_MINI":
        tectoy = new TecToy(Dispositivo.T2_MINI, context);
        break;
      case "D2_MINI":
        tectoy = new TecToy(Dispositivo.D2_MINI, context);
        break;
      case "D2S":
        tectoy = new TecToy(Dispositivo.D2S, context);
        break;
      case "V2":
        tectoy = new TecToy(Dispositivo.V2, context);
        break;
      case "V2_PRO":
        tectoy = new TecToy(Dispositivo.V2_PRO, context);
        break;
      case "K2":
        tectoy = new TecToy(Dispositivo.K2, context);
        break;
      case "K2_MINI":
        tectoy = new TecToy(Dispositivo.K2_MINI, context);
        break;
      case "T2S":
        tectoy = new TecToy(Dispositivo.T2S, context);
        break;
      case "L2Ks":
        tectoy = new TecToy(Dispositivo.L2Ks, context);
        break;
      case "L2s":
        tectoy = new TecToy(Dispositivo.L2s, context);
        break;
    }
    return tectoy;
  }
  public void status(Context context) {
    try {
      Toast.makeText(context, StatusImpressora.TextoStatus(tectoy.statusImpressora().obtemStatus()), Toast.LENGTH_SHORT).show();
      Utils.logErr("status", StatusImpressora.TextoStatus(tectoy.statusImpressora().obtemStatus()));
    } catch (Exception e) {
      Utils.logErr("status_error", e.toString());
      e.printStackTrace();
    }
  }
  public static void start(String pathImage) {
    try {
//      Handler handler = new Handler(Looper.getMainLooper());
//      handler.post(() -> {
//        Toast.makeText(context, StatusImpressora.TextoStatus(tectoy.statusImpressora().obtemStatus()), Toast.LENGTH_LONG).show();
//      });

      TecToyPrinter.tectoy.imprimirImagem(pathImage);
      Utils.log("start");
    } catch (Exception e) {
//      Handler handler = new Handler(Looper.getMainLooper());
//      handler.post(() -> {
//        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//      });

      Utils.logErr("start_error", e.toString());
      e.printStackTrace();
    }
  }
}
