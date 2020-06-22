package com.vothanhhien.automarkmobile.models;

public class KhungTraLoi {
    private long id;
    private long mau;
    private int x;
    private int y;
    private int width;
    private int height;
    private int cols;
    private int rows;

    public KhungTraLoi(long id, long mau, int x, int y, int width, int height, int cols, int rows) {
        this.id = id;
        this.mau = mau;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cols = cols;
        this.rows = rows;
    }
    public KhungTraLoi(){

    }
    public void capnhat(int x, int y, int width, int height, int cols, int rows){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cols = cols;
        this.rows = rows;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMau() {
        return mau;
    }

    public void setMau(long mau) {
        this.mau = mau;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
