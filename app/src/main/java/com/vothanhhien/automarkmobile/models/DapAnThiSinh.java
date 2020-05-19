package com.vothanhhien.automarkmobile.models;

public class DapAnThiSinh {
    long typeSheet;
    long id;
    String sbd;
    String code;
    String answers;
    int correctNumber;
    byte[] image;

    public int getCorrectNumber() {
        return correctNumber;
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public DapAnThiSinh(long id, long typeSheet, String sbd, String answers, String code, int correctNumber, byte[] image) {
        this.typeSheet = typeSheet;
        this.id = id;
        this.sbd = sbd;
        this.code = code;
        this.answers = answers;
        this.correctNumber = correctNumber;
        this.image = image;
    }
    public DapAnThiSinh(long typeSheet, String sbd, String answers, String code, int correctNumber, byte[] image) {
        this.typeSheet = typeSheet;
        this.id = id;
        this.sbd = sbd;
        this.code = code;
        this.answers = answers;
        this.correctNumber = correctNumber;
        this.image = image;
    }

    public long getTypeSheet() {
        return typeSheet;
    }

    public void setTypeSheet(long typeSheet) {
        this.typeSheet = typeSheet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSbd() {
        return sbd;
    }

    public void setSbd(String sbd) {
        this.sbd = sbd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
