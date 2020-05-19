package com.vothanhhien.automarkmobile.models;

import org.opencv.core.Scalar;

public class LuaChon {

    private int iChoice;
    private int jChoice;
    private int drawX;
    private int drawY;
    private int zeroCount;
    private Scalar color;

    public LuaChon(int iChoice, int jChoice, int drawX, int drawY, int zeroCount) {
        this.iChoice = iChoice;
        this.jChoice = jChoice;
        this.drawX = drawX;
        this.drawY = drawY;
        this.zeroCount = zeroCount;
        this.color = new Scalar(0,255,0);
    }



    public LuaChon(int iChoice, int jChoice, int drawX, int drawY) {
        this.iChoice = iChoice;
        this.jChoice = jChoice;
        this.drawX = drawX;
        this.drawY = drawY;
        this.color = new Scalar(0,255,0);
    }

    public void setColor(Scalar color) {
        this.color = color;
    }
    public int getiChoice() {
        return iChoice;
    }

    public void setiChoice(int iChoice) {
        this.iChoice = iChoice;
    }

    public int getjChoice() {
        return jChoice;
    }

    public void setjChoice(int jChoice) {
        this.jChoice = jChoice;
    }

    public int getDrawX() {
        return drawX;
    }

    public void setDrawX(int drawX) {
        this.drawX = drawX;
    }

    public int getDrawY() {
        return drawY;
    }

    public void setDrawY(int drawY) {
        this.drawY = drawY;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(int zeroCount) {
        this.zeroCount = zeroCount;
    }

    public Scalar getColor() {
        return color;
    }
}
