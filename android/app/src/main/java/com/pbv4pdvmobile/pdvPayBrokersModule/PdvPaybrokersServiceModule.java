package com.pbv4pdvmobile.pdvPayBrokersModule;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.pbv4pdvmobile.constants.ConstLib;
import com.pbv4pdvmobile.helpers.Helper;
import com.pbv4pdvmobile.pdvPayBrokersModule.drawLayout.DrawLayout;
import com.pbv4pdvmobile.pdvPayBrokersModule.printer.PrinterConfig;
import com.pbv4pdvmobile.helpers.PrintHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PdvPaybrokersServiceModule extends ReactContextBaseJavaModule {
  private PackageInfo getPackageInfo() throws Exception {
    return getReactApplicationContext().getPackageManager().getPackageInfo(getReactApplicationContext().getPackageName(), 0);
  }

  public PdvPaybrokersServiceModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
  @NonNull
  @Override
  public String getName() {
    return "PdvPaybrokersService";
  }

  @ReactMethod
  public void getNameTest(Promise promise) {
    promise.resolve("PdvPaybrokersService");
  }

  @ReactMethod
  public Context getContextApp() {
   return Objects.requireNonNull(getCurrentActivity()).getApplicationContext();
  }

  @ReactMethod
  public void printDepositQrCode(String jsonStr, final Promise promise) {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable runnableTask = () -> {
      PrinterConfig.getInstance().init(getContextApp());

      final JSONObject jsonObject;
      try {
        jsonObject = new JSONObject(jsonStr);

        final String encodedString = jsonObject.get("qr_code_image").toString();
        final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        DrawLayout.initNewImagePrint();
        DrawLayout.setFontSize(30);
        DrawLayout.setBold(true);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("QRCode de Pagamento PIX");
        DrawLayout.addTextLine("");

        DrawLayout.addImage(decodedByte);

        DrawLayout.setFontSize(25);
        DrawLayout.setAlign(1);
        DrawLayout.setLineSpacing(30);
        DrawLayout.addTextLine("CPF: " + jsonObject.get("document"));
        DrawLayout.addTextLine("Valor: " + jsonObject.get("value"));

        DrawLayout.setFontSize(28);
        DrawLayout.setBold(true);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("Este QRCode pode ser pago");
        DrawLayout.addTextLine("até 28/07/2023 ás 12:45");
        DrawLayout.addTextLine("");
        DrawLayout.addTextLine("");
        DrawLayout.addTextLine("");

        DrawLayout.generateImagePrint("imagem");

        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/print/imagem");
        PrinterConfig.getInstance().printBitmap(bitmap);
        PrinterConfig.getInstance().start();

        promise.resolve("success");

      } catch (Exception e) {
        promise.reject(null, e.getMessage());
      }
    };
    executor.execute(runnableTask);
    executor.shutdown();
  }
  @ReactMethod
  public void  printVoucherQrCode(String jsonStr, final Promise promise) {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable runnableTask = () -> {
      PrinterConfig.getInstance().init(getContextApp());

      final JSONObject jsonObject;
      final int BARCODE_WIDTH  = 250;
      final int BARCODE_HEIGHT = 90;
      final int QRCODE_WIDTH   = 120;
      final int QRCODE_HEIGHT  = 120;

      try {
        jsonObject = new JSONObject(jsonStr);

        final String name = jsonObject.get("name").toString();
        final String barcode = jsonObject.get("barcode").toString();
        final String qrcode = jsonObject.get("qr_code_image").toString();

        Bitmap logoBitmap = BitmapFactory.decodeFile(Helper.getImageFromPath(ConstLib.LOGO_PATH));
        Bitmap barcodeBitmap = PrintHelper.generateBarcode(barcode, BARCODE_WIDTH, BARCODE_HEIGHT);
        Bitmap qrCodeBitmap = PrintHelper.generateQrCodByStringBase64(qrcode, QRCODE_WIDTH, QRCODE_HEIGHT);
        Bitmap combinedBitmap = Helper.combineBitmaps(barcodeBitmap, qrCodeBitmap);

        DrawLayout.initNewImagePrint();

        DrawLayout.setLandscape(true);
        DrawLayout.setPositionPaperLandscape(1.5f);
        DrawLayout.setWidthPaperLandscape(600);

        DrawLayout.setFontSize(20);
        DrawLayout.setBold(true);
        DrawLayout.setAlign(1);
        DrawLayout.addImage(logoBitmap);
        DrawLayout.addImage(combinedBitmap);

        DrawLayout.setFontSize(20);
        DrawLayout.setAlign(1);
        DrawLayout.setBold(true);
        DrawLayout.addTextLine(name + " - " + jsonObject.get("document"));
        DrawLayout.setFontSize(20);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("   Recebedor");
        DrawLayout.addTextLine("");

        DrawLayout.addTextLine("   0,79USD  AB12345678910");
        DrawLayout.addTextLine("   15.10.2023      10:22:04      Voucher");
        DrawLayout.setFontSize(22);
        DrawLayout.setAlign(1);
        DrawLayout.setBold(true);
        DrawLayout.addTextLine("BRL   " + jsonObject.get("value").toString().replace("R$", ""));
        DrawLayout.addTextLine("");

        DrawLayout.setFontSize(20);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("Válido até 15/10/2023 10:00");

        DrawLayout.generateImagePrint(ConstLib.FILE_PRINT_NAME);

        Bitmap bitmap = BitmapFactory.decodeFile(Helper.getImageFromPath("/Download/print/imagem"));
        PrinterConfig.getInstance().printBitmap(bitmap);
        PrinterConfig.getInstance().start();

        promise.resolve("success");
      } catch (Exception e) {
        promise.reject(null, e.getMessage());
      }
    };
    executor.execute(runnableTask);
    executor.shutdown();
  }

  @ReactMethod
  public void printWithdraw(String jsonStr, final Promise promise) {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable runnableTask = () -> {
      PrinterConfig.getInstance().init(getContextApp());

      final JSONObject jsonObject;
      try {
        jsonObject = new JSONObject(jsonStr);

        DrawLayout.initNewImagePrint();
        DrawLayout.setFontSize(30);
        DrawLayout.setBold(true);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("PIX Comprovante");
        DrawLayout.addTextLine("de pagamento");
        DrawLayout.addTextLine("");
        DrawLayout.setLineSpacing(30);

        DrawLayout.setFontSize(25);
        DrawLayout.setAlign(1);
        DrawLayout.addTextLine("Transação: " + Utils.abbreviate(jsonObject.get("_id").toString(), 20));
        DrawLayout.setLineSpacing(30);

//        DrawLayout.addTextLine("Identificador:  " +  Utils.abbreviate(jsonObject.get("endToEndId").toString(), 15));
//        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("Valor:                                           " + jsonObject.get("value"));
        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("Data/Hora:             " + jsonObject.get("createdAt"));
        DrawLayout.setLineSpacing(30);

//        DrawLayout.addTextLine("Descrição:             " + jsonObject.get("description"));
//        DrawLayout.setLineSpacing(30);

        DrawLayout.setBold(true);
        DrawLayout.setLineSpacing(30);
        DrawLayout.addTextLine("");
        DrawLayout.addTextLine("Pagador                                                   ");

        DrawLayout.addTextLine("Instituição:     " +  Utils.abbreviate(jsonObject.get("merchant_name").toString(), 16));
        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("CNPJ:                             " + jsonObject.get("receiver_document"));
        DrawLayout.setLineSpacing(30);

//        DrawLayout.addTextLine("Agência:                                           " + jsonObject.get("paid_bank_agency"));
//        DrawLayout.setLineSpacing(30);
//
//        DrawLayout.addTextLine("Conta corrente: " +  Utils.abbreviate(jsonObject.get("paid_bank_account").toString(), 14));
//        DrawLayout.setLineSpacing(30);

        DrawLayout.setBold(true);
        DrawLayout.setLineSpacing(30);
        DrawLayout.addTextLine("");
        DrawLayout.addTextLine("Destinatário                                           ");

        DrawLayout.addTextLine("Instituição:  " + Utils.abbreviate(jsonObject.get("bank").toString(), 17));
        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("Nome: " + Utils.abbreviate(jsonObject.get("receiver_name").toString(), 21));
        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("CPF:                                " + jsonObject.get("receiver_document"));
        DrawLayout.setLineSpacing(30);

        DrawLayout.addTextLine("Chave:                     " + jsonObject.get("pix_key"));
        DrawLayout.addTextLine("                                       ");
        DrawLayout.addTextLine("                                       ");
        DrawLayout.addTextLine("                                       ");

        DrawLayout.generateImagePrint("imagem");

        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/print/imagem");
        PrinterConfig.getInstance().printBitmap(bitmap);
        PrinterConfig.getInstance().start();

        promise.resolve("success");

      } catch (Exception e) {
        promise.reject(null, e.getMessage());
      }
    };
    executor.execute(runnableTask);
    executor.shutdown();
  }

  @ReactMethod
  public void printExtract(String jsonStr, final Promise promise) {
    final ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable runnableTask = () -> {
      PrinterConfig.getInstance().init(getContextApp());

        final JSONObject jsonObject;
        try {
          jsonObject = new JSONObject(jsonStr);
          final JSONArray extracts = jsonObject.getJSONArray("list");

          DrawLayout.initNewImagePrint();
          DrawLayout.setFontSize(30);
          DrawLayout.setBold(true);
          DrawLayout.setAlign(1);
          DrawLayout.addTextLine("Extrato de movimentações");
          DrawLayout.addTextLine("Data/Hora impressões");
          DrawLayout.addTextLine("");

          for (int i = 0; i < extracts.length(); i++) {
              JSONObject o;
              o = extracts.getJSONObject(i);
              DrawLayout.setFontSize(20);
              DrawLayout.setAlign(1);
              DrawLayout.setLineSpacing(30);
              DrawLayout.addTextLine(o.get("date_time") + "                 " + o.get("value"));
          }

          DrawLayout.setFontSize(20);
          DrawLayout.setAlign(1);
          DrawLayout.setLineSpacing(30);
          DrawLayout.addTextLine("total" + ":" + jsonObject.get("total") + "                                           ");
          DrawLayout.addTextLine("");
          DrawLayout.addTextLine("");
          DrawLayout.addTextLine("");

          DrawLayout.generateImagePrint("imagem");

          Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/print/imagem");
          PrinterConfig.getInstance().printBitmap(bitmap);
          PrinterConfig.getInstance().start();

          promise.resolve("success");

        } catch (Exception e) {
          promise.reject(null, e.getMessage());
        }
    };
    executor.execute(runnableTask);
    executor.shutdown();
  }
}
