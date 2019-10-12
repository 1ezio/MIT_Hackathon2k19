package com.doc.docrepos.models;

public class rec_reg_model {
    public rec_reg_model(String recuser, String recpass) {
        this.recuser = recuser;
        this.recpass = recpass;
    }

    public String getRecuser() {
        return recuser;
    }

    public void setRecuser(String recuser) {
        this.recuser = recuser;
    }

    public String getRecpass() {
        return recpass;
    }

    public void setRecpass(String recpass) {
        this.recpass = recpass;
    }

    private String recuser, recpass;

    public rec_reg_model() {
    }
}
