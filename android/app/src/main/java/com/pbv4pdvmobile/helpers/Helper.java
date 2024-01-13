package com.pbv4pdvmobile.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Helper {
  public static String getImageFromPath(String path){
    return Environment.getExternalStorageDirectory().getAbsolutePath() + path;
  }

  public static Bitmap combineBitmaps(Bitmap barcode, Bitmap qrCode) {
    int totalWidth = barcode.getWidth() + qrCode.getWidth();
    int totalHeight = Math.max(barcode.getHeight(), qrCode.getHeight());

    // Criar um bitmap combinado com largura total para acomodar ambos os bitmaps
    Bitmap combinedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);

    // Criar um canvas para desenhar os bitmaps combinados
    Canvas canvas = new Canvas(combinedBitmap);


    // Calcular as posições para centralizar vertical e horizontalmente os bitmaps
    int leftBarcode = 0; // À esquerda do bitmap combinado
    int topBarcode = (totalHeight - barcode.getHeight()) / 4;

    int leftQrCode = barcode.getWidth(); // À direita do bitmap de código de barras
    int topQrCode = (totalHeight - qrCode.getHeight()) / 4;

    // Desenhar os bitmaps centralizados
    canvas.drawBitmap(barcode, leftBarcode, topBarcode, null);
    canvas.drawBitmap(qrCode, leftQrCode, topQrCode, null);

    return combinedBitmap;
  }

  public static void saveBitmap(Bitmap bitmap, String fileName) {
    // Obter o diretório de armazenamento externo público
    File directory =new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/print/");

    // Criar um arquivo no diretório com o nome fornecido
    File file = new File(directory, fileName);

    try {
      // Criar um fluxo de saída de arquivo
      FileOutputStream fos = new FileOutputStream(file);

      // Comprimir e salvar o bitmap no formato PNG
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

      // Fechar o fluxo de saída
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean resizeImage(String  imageName, int newWidth,  int newHeight) {
    // Get the path to the Download folder
    String downloadFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

    // Create a File object for the original image file
    File originalImageFile = new File(downloadFolderPath, imageName);

    // Check if the original file exists
    if (originalImageFile.exists()) {
      // Load the original image
      Bitmap originalBitmap = BitmapFactory.decodeFile(originalImageFile.getAbsolutePath());

      // Resize the image
      Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);

      // Create a File object for the resized image file
      File resizedImageFile = new File(downloadFolderPath, "resized_" + imageName);

      try {
        // Save the resized image to the Downloads folder
        FileOutputStream outputStream = new FileOutputStream(resizedImageFile);
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception
      }
      return true;
    } else {
      return false;
    }
  }

}
