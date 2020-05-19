package com.vothanhhien.automarkmobile.models;

public class Khoi {
    private long blockID;
    private String name;
    private long sheet;

    public Khoi(String name, long sheet) {
        this.name = name;
        this.sheet = sheet;
    }

    public Khoi(long blockID, String name, long sheet) {
        this.blockID = blockID;
        this.name = name;
        this.sheet = sheet;
    }

    public long getBlockID() {
        return blockID;
    }

    public void setBlockID(long blockID) {
        this.blockID = blockID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSheet() {
        return sheet;
    }

    public void setSheet(long sheet) {
        this.sheet = sheet;
    }
}
