package com.pbv4pdvmobile.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintHelper {

  public static Bitmap generateBarcode(String text, int width, int height) {
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      // Gere a matriz de bits para o código de barras Code 128
      BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128, width, height);
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

      // Preencha os pixels do bitmap de acordo com a matriz de bits
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
        }
      }

      return bitmap;
    } catch (WriterException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Bitmap generateQRCode(String data, int width, int height) {
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      // Gere a matriz de bits para o código QR
      BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

      // Crie um bitmap para o código QR
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

      // Preencha os pixels do bitmap com a matriz de bits do código QR
      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
        }
      }

      return bitmap;
    } catch (WriterException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Bitmap generateQrCodByStringBase64(String stringBase64) {
    final String pureBase64Encoded = stringBase64.substring(stringBase64.indexOf(",")  + 1);
    byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
  }

  public static Bitmap generateQrCodByStringBase64(String stringBase64, int width, int height) {
    final String pureBase64Encoded = stringBase64.substring(stringBase64.indexOf(",")  + 1);
    byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    return Bitmap.createScaledBitmap(bitmap, width, height, false);
  }
}