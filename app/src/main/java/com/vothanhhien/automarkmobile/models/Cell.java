package com.vothanhhien.automarkmobile.models;

public class Cell {
    public int x,y,zeroCount;

    public Cell(int x, int y, int zeroCount) {
        this.x = x;
        this.y = y;
        this.zeroCount = zeroCount;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(int zeroCount) {
        this.zeroCount = zeroCount;
    }
}
