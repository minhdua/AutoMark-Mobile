package com.vothanhhien.automarkmobile.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MauTraLoi2 {
    private long sheetID;
    private String code;
    private String answers;
    private long set;
    private byte[] image;
    private String date;

    // to get data
    public MauTraLoi2(long sheetID, long set, String code, String answers, String date, byte[] image) {
        this(set,code,answers,image);
        this.date = date;
        this.sheetID = sheetID;
    }
    // to add data
    public MauTraLoi2(long set, String code, String answers, byte[] image) {
        this.set = set;
        this.code = code;
        this.date = getCurrentDateString();
        this.answers = answers;
        this.image=image;
    }
    public byte[] getImage() {
        return image;
    }
    public String getDate(){
        return date;
    }

    private String getCurrentDateString() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = new Date();
        String currentDate = timeStampFormat.format(myDate);
        return currentDate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public long getSheetID() {
        return sheetID;
    }

    public String getAnswers() {
        return answers;
    }

    public long getSet() {
        return set;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSheetID(long sheetID) {
        this.sheetID = sheetID;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public void setSet(long set) {
        set = set;
    }

    //=============================== Sheet Sample Class ========================== //

    public static class MauTraLoiSample extends MauTraLoi2 {
        private String correctAnswers;

        public MauTraLoiSample(long sheetID, long set, String code, String answers, String date, byte[] image) {
            super(sheetID,set, code, answers,date,image);
            correctAnswers = getAnswers();
        }

        public MauTraLoiSample(long set, String code, String answers, byte[] image) {
            super(set, code, answers,image);
            correctAnswers = getAnswers();
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }

        public void setCorrectAnswers(String correctAnswers) {
            this.correctAnswers = correctAnswers;
        }
    }

    //=============================== Sheet Instance Class ========================== //

    public static class MauTraLoiInstance extends MauTraLoi2 {
        private String yourAnswers;
        private String idNumber;

        public MauTraLoiInstance(long sheetID, long set, String code, String answers, String date, byte[] image, String idNumber) {
            super(sheetID, set,code, answers,date,image);
            this.idNumber = idNumber;
            this.yourAnswers = getAnswers();
        }
        public MauTraLoiInstance(long set, String code, String answers, byte[] image, String idNumber) {
            super(set,code, answers,image);
            this.idNumber = idNumber;
            this.yourAnswers = getAnswers();
        }
        public String getYourAnswers() {
            return yourAnswers;
        }

        public void setYourAnswers(String yourAnswers) {
            this.yourAnswers = yourAnswers;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}



