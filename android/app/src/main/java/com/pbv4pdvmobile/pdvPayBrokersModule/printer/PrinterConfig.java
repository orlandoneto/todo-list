package com.pbv4pdvmobile.pdvPayBrokersModule.printer;

import android.content.Context;
import android.graphics.Bitmap;

import com.pax.dal.IPrinter;
import com.pax.dal.entity.EFontTypeAscii;
import com.pax.dal.entity.EFontTypeExtCode;
import com.pax.dal.exceptions.PrinterDevException;
import com.pbv4pdvmobile.pdvPayBrokersModule.Utils;

public class PrinterConfig {
    private static PrinterConfig printerTester;
    private IPrinter printer;

    public static PrinterConfig getInstance() {
        if (printerTester == null) {
            printerTester = new PrinterConfig();
        }
        return printerTester;
    }

    public void init(Context context) {
        try {
            printer = new Printer(context).getDal().getPrinter();
            printer.init();
            Utils.log("init");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("init", e.toString());
        }
    }

    public String getStatus() {
        try {
            int status = printer.getStatus();
          Utils.log("getStatus");
            return statusCode2Str(status);
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("getStatus", e.toString());
            return "";
        }

    }

    public void fontSet(EFontTypeAscii asciiFontType, EFontTypeExtCode cFontType) {
        try {
            printer.fontSet(asciiFontType, cFontType);
          Utils.log("fontSet");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("fontSet", e.toString());
        }

    }

    public void spaceSet(byte wordSpace, byte lineSpace) {
        try {
            printer.spaceSet(wordSpace, lineSpace);
          Utils.log("spaceSet");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("spaceSet", e.toString());
        }
    }

    public void printStr(String str, String charset) {
        try {
            printer.printStr(str, charset);
          Utils.log("printStr");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("printStr", e.toString());
        }

    }

    public void step(int b) {
        try {
            printer.step(b);
          Utils.log("setStep");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("setStep", e.toString());
        }
    }

    public void printBitmap(Bitmap bitmap) {
        try {
            printer.printBitmap(bitmap);
          Utils.log("printBitmap");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("printBitmap", e.toString());
        }
    }

    public String start() {
        try {
            int res = printer.start();
          Utils.log("start");
            return statusCode2Str(res);
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("start", e.toString());
            return "";
        }

    }

    public void leftIndents(short indent) {
        try {
            printer.leftIndent(indent);
          Utils.log("leftIndent");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("leftIndent", e.toString());
        }
    }

    public int getDotLine() {
        try {
            int dotLine = printer.getDotLine();
          Utils.log("getDotLine");
            return dotLine;
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("getDotLine", e.toString());
            return -2;
        }
    }

    public void setGray(int level) {
        try {
            printer.setGray(level);
          Utils.log("setGray");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("setGray", e.toString());
        }

    }

    public void setDoubleWidth(boolean isAscDouble, boolean isLocalDouble) {
        try {
            printer.doubleWidth(isAscDouble, isLocalDouble);
          Utils.log("doubleWidth");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("doubleWidth", e.toString());
        }
    }

    public void setDoubleHeight(boolean isAscDouble, boolean isLocalDouble) {
        try {
            printer.doubleHeight(isAscDouble, isLocalDouble);
          Utils.log("doubleHeight");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("doubleHeight", e.toString());
        }

    }

    public void setInvert(boolean isInvert) {
        try {
            printer.invert(isInvert);
          Utils.log("setInvert");
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("setInvert", e.toString());
        }

    }

    public String cutPaper(int mode) {
        try {
            printer.cutPaper(mode);
          Utils.log("cutPaper");
            return "cut paper successful";
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("cutPaper", e.toString());
            return e.toString();
        }
    }

    public String getCutMode() {
        String resultStr = "";
        try {
            int mode = printer.getCutMode();
          Utils.log("getCutMode");
            switch (mode) {
                case 0:
                    resultStr = "Only support full paper cut";
                    break;
                case 1:
                    resultStr = "Only support partial paper cutting ";
                    break;
                case 2:
                    resultStr = "support partial paper and full paper cutting ";
                    break;
                case -1:
                    resultStr = "No cutting knife,not support";
                    break;
                default:
                    break;
            }
            return resultStr;
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("getCutMode", e.toString());
            return e.toString();
        }
    }

    public String preSetCutMode(int mode) {
        try {
            printer.presetCutPaper(mode);
          Utils.log("preSetCutMode");
            return "set cut paper mode successful";
        } catch (PrinterDevException e) {
            e.printStackTrace();
          Utils.logErr("getCutMode", e.toString());
            return e.getLocalizedMessage();
        }
    }

    public String statusCode2Str(int status) {
        String res = "";
        switch (status) {
            case 0:
                res = "Success ";
                break;
            case 1:
                res = "TecToyPrinter is busy ";
                break;
            case 2:
                res = "Out of paper ";
                break;
            case 3:
                res = "The format of print data packet error ";
                break;
            case 4:
                res = "TecToyPrinter malfunctions ";
                break;
            case 8:
                res = "TecToyPrinter over heats ";
                break;
            case 9:
                res = "TecToyPrinter voltage is too low";
                break;
            case -16:
                res = "Printing is unfinished ";
                break;
            case -4:
                res = " The printer has not installed font library ";
                break;
            case -2:
                res = "Data package is too long ";
                break;
            default:
                break;
        }
        return res;
    }
}
