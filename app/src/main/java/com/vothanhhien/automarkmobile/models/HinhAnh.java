package com.vothanhhien.automarkmobile.models;

import org.opencv.core.Mat;

import java.io.Serializable;

public class HinhAnh implements Serializable {
  public Mat image;
  public BaiThi baithi;
    public HinhAnh(Mat image, BaiThi baithi) {
        this.image = image;
        this.baithi = baithi;
    }
}