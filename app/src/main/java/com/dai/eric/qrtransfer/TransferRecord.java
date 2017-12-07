package com.dai.eric.qrtransfer;

/**
 * Created by Eric on 2017-12-01.
 */

public class TransferRecord {

    private String type;
    private String date;
    private String userEmail;

    public TransferRecord(String type, String date, String userEmail){
        this.type = type;
        this.date = date;
        this.userEmail = userEmail;
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

    public String getUserEmail(){
        return this.userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

}
