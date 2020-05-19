package com.vothanhhien.automarkmobile.models;

public class DapAn {
    long id;
    long typeSheet;
    String answers;
    String code;

    public DapAn(long id, long typeSheet, String answers, String code) {
        this.id = id;
        this.typeSheet = typeSheet;
        this.answers = answers;
        this.code = code;
    }
    public DapAn(long typeSheet, String answers, String code) {
        this.typeSheet = typeSheet;
        this.answers = answers;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeSheet() {
        return typeSheet;
    }

    public void setTypeSheet(long typeSheet) {
        this.typeSheet = typeSheet;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
