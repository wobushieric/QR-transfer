package com.dai.eric.qrtransfer;

/**
 * Created by Eric on 2017-12-01.
 */

public class TransferRecord {

    private int type;
    private String date;

    public TransferRecord(int type, String date){
        this.type = type;
        this.date = date;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

}
