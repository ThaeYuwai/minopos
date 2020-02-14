package com.waiway.minipos.Model;

public class ColorModel {

    public int ColorId,ColorRed,ColorGreen,ColorBlue;

    public ColorModel(int colorId, int colorRed, int colorGreen, int colorBlue) {
        ColorId = colorId;
        ColorRed = colorRed;
        ColorGreen = colorGreen;
        ColorBlue = colorBlue;
    }

    public ColorModel() {
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }

    public int getColorRed() {
        return ColorRed;
    }

    public void setColorRed(int colorRed) {
        ColorRed = colorRed;
    }

    public int getColorGreen() {
        return ColorGreen;
    }

    public void setColorGreen(int colorGreen) {
        ColorGreen = colorGreen;
    }

    public int getColorBlue() {
        return ColorBlue;
    }

    public void setColorBlue(int colorBlue) {
        ColorBlue = colorBlue;
    }
}
