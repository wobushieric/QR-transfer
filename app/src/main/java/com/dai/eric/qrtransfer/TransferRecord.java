package com.dai.eric.qrtransfer;

/**
 * Created by Eric on 2017-12-01.
 */

public class TransferRecord {

    private String type;
    private String date;

    public TransferRecord(String type, String date){
        this.type = type;
        this.date = date;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

}
