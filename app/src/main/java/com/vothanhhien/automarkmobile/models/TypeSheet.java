package com.vothanhhien.automarkmobile.models;

public class TypeSheet {
    private long typeSheetID;
    private String name;
    private  byte[] image;

    public TypeSheet(long typeSheetID, String name,byte[] image) {
        this.typeSheetID = typeSheetID;
        this.name = name;
        this.image = image;
    }
    public TypeSheet( String name,byte[] image) {
        this.name = name;
        this.image = image;
    }

    public long getTypeSheetID() {
        return typeSheetID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setTypeSheetID(long typeSheetID) {
        this.typeSheetID = typeSheetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}