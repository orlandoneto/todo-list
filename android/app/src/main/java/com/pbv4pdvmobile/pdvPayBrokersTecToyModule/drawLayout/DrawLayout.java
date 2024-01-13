package com.pbv4pdvmobile.pdvPayBrokersTecToyModule.drawLayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DrawLayout {
  private int countPrint = 0;
  private int countImages = 0;
  private int countPassword = 0;
  private String messageCard = null;
  private String getPassword = null;
  private static Bitmap lastQrImage;
  private static Boolean landscape = false;
  private static float positionPaperLandscape = 1.2f;
  private static int widthPaperLandscape = 800;
  private static int lineSpacing = 25;
  private static int fontSize = 16;
  private static int align = 0; //0 = esquerda, 1 = centro, 2 = direita
  private static float sideMarginPercentage = 1; //Em porcentagem
  private static float topMarginPercentage = 1; //Em porcentagem
  private static float bottomMarginPercentage = 2; //Em porcentagem
  private static int width = 340;
  private static boolean bold = false;
  private static String breakLineAuxString;
  private static Boolean antiAliasing;
  private static ArrayList<Piece> pieces;
  private static String fontFamily = "sans-serif";
  private static class Piece {
    protected int height;
    protected Paint paint;

    public int getHeight() {
      return height;
    }

    public Paint getPaint() {
      return paint;
    }
  }

  public static void initNewImagePrint() {
    lineSpacing = 10;
    fontSize = 50;
    align = 0;
    sideMarginPercentage = 0;
    topMarginPercentage = 1;
    bottomMarginPercentage = 2;
    width = 400;
    bold = false;
    breakLineAuxString = null;
    antiAliasing = true;

    pieces = new ArrayList<>();
  }

  public static void setBold(boolean _bold) {
    bold = _bold;
  }

  public static void setLineSpacing(int _lineSpacing) {
    lineSpacing = _lineSpacing;
  }

  public static void setFontSize(int _fontSize) {
    fontSize = _fontSize;
  }

  public static void setFontFamily(String _fontFamily) {
    fontFamily = _fontFamily;
  }

  public static void setAlign(int _align) {
    align = _align;
  }

  public static void setAntiAliasing(Boolean aliasing) {
    antiAliasing = aliasing;
  }

  public static void setSideMarginPercentage(float _sideMarginPercentage) {
    sideMarginPercentage = _sideMarginPercentage;
  }

  public static void setTopMarginPercentage(float _topMarginPercentage) {
    topMarginPercentage = _topMarginPercentage;
  }

  public static void setBottomMarginPercentage(float _bottomMarginPercentage) {
    bottomMarginPercentage = _bottomMarginPercentage;
  }

  public static void setImageWidth(int _width) {
    width = _width;
  }

  public static String returnNumberFi(Integer total, Integer actual) {
    return actual + "/" + total;
  }

  public static void setLandscape(boolean _landscape) {
    landscape = _landscape;
  }
  public static void setPositionPaperLandscape(float _positionPaperLandscape) {
    positionPaperLandscape = _positionPaperLandscape;
  }
  public static void setWidthPaperLandscape(int _widthPaperLandscape) {
    widthPaperLandscape = _widthPaperLandscape;
  }

  private static class TextPiece extends Piece {
    private final String text;

    public TextPiece(String _text) {
      paint = new Paint();
      paint.setAntiAlias(antiAliasing);
      paint.setColor(Color.BLACK);
      paint.setTextSize(fontSize);
      paint.setStyle(Paint.Style.FILL);
      paint.setTypeface(Typeface.create(fontFamily, Typeface.NORMAL));
      paint.setFakeBoldText(bold);

      if (align == 1) {
        paint.setTextAlign(Paint.Align.CENTER);
      } else if (align == 2) {
        paint.setTextAlign(Paint.Align.RIGHT);
      } else if (align == 3){
        paint.setTextAlign(Paint.Align.LEFT);
      }

      //Calcula se cabe o texto completo sem quebra linha
      int charactersAmount = paint.breakText(_text, true, width, null);

      if (charactersAmount < _text.length()) {
        breakLineAuxString = _text.substring(charactersAmount);
        _text = _text.substring(0, charactersAmount);
      } else {
        breakLineAuxString = null;
      }

      text = _text;

      Rect rectText = new Rect();
      paint.getTextBounds(text, 0, text.length(), rectText);
      height = rectText.height() + lineSpacing;
    }

    public String getText() {
      return text;
    }
  }

  public static void addTextLine(String text) {
    pieces.add(new TextPiece(text));

    while (breakLineAuxString != null && !breakLineAuxString.isEmpty()) {
      pieces.add(new TextPiece(breakLineAuxString));
    }
  }

  private static class ImagePiece extends Piece {
    private Bitmap image;

    public ImagePiece(byte[] _imageBytes) {
      SetImage(BitmapFactory.decodeByteArray(_imageBytes, 0, _imageBytes.length));
    }

    public ImagePiece(Bitmap _image) {
      SetImage(_image);
    }

    private void SetImage(Bitmap _image) {
      image = _image;
      height = image.getHeight() + lineSpacing;

      paint = new Paint(Paint.ANTI_ALIAS_FLAG);

      if (align == 1) {
        paint.setTextAlign(Paint.Align.CENTER);
      } else if (align == 2) {
        paint.setTextAlign(Paint.Align.RIGHT);
      } else {
        paint.setTextAlign(Paint.Align.LEFT);
      }
    }

    public Bitmap getImage() {
      return image;
    }
  }

  public static void addImage(byte[] imageBytes) {
    pieces.add(new ImagePiece(imageBytes));
  }

  public static void addImage(Bitmap image) {
    if (image == null) {
      return;
    }
    pieces.add(new ImagePiece(image));
  }

  public static void generateImagePrint(String fileName) {
    Bitmap newBitmap = null;

    try {
      int _sideMargin = (int) (width * sideMarginPercentage) / 100;
      int _bottomMargin = (int) (width * bottomMarginPercentage) / 100;
      int _topMargin = (int) (width * topMarginPercentage) / 100;

      Iterator<Piece> iterator = pieces.iterator();
      int height = iterator.next().getHeight() + _topMargin + _bottomMargin;
      while (iterator.hasNext()) {
        height += iterator.next().getHeight();
      }

      if(landscape) {
        newBitmap = Bitmap.createBitmap(width, widthPaperLandscape, Bitmap.Config.ARGB_8888);
      }else{
        newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      }
      Canvas newCanvas = new Canvas(newBitmap);

      Paint paint2 = new Paint();
      paint2.setAntiAlias(antiAliasing);
      paint2.setColor(Color.WHITE);
      paint2.setStyle(Paint.Style.FILL);
      newCanvas.drawPaint(paint2);

      if(landscape) {
        newCanvas.rotate(-90, newCanvas.getWidth() / positionPaperLandscape, newCanvas.getWidth() / positionPaperLandscape);
      }

      iterator = pieces.iterator();
      int currentHeight = _topMargin;
      while (iterator.hasNext()) {
        Piece currentPiece = iterator.next();

        int x = _sideMargin;
        if (currentPiece.getPaint().getTextAlign() == Paint.Align.CENTER) {
          x = width / 2;
        } else if (currentPiece.getPaint().getTextAlign() == Paint.Align.RIGHT) {
          x = width - _sideMargin;
        }else if (currentPiece.getPaint().getTextAlign() == Paint.Align.LEFT) {
          x = width - _sideMargin;
        }

        if (currentPiece instanceof TextPiece) {
          currentHeight += currentPiece.getHeight();
          newCanvas.drawText(((TextPiece) currentPiece).getText(), x, currentHeight, currentPiece.getPaint());
        } else if (currentPiece instanceof ImagePiece) {
          newCanvas.drawBitmap(((ImagePiece) currentPiece).getImage(), (x - (((ImagePiece) currentPiece).getImage().getWidth() / 2)), currentHeight, currentPiece.getPaint());
          currentHeight += currentPiece.getHeight();
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/print/");
      if (!dir.exists()) {
        dir.mkdirs();
      }
      File file = new File(dir, fileName);
      FileOutputStream fOut = new FileOutputStream(file);

      newBitmap.compress(Bitmap.CompressFormat.PNG, 1, fOut);
      fOut.flush();
      fOut.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
