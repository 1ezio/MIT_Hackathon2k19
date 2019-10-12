package com.doc.docrepos.models;

public class test_lab_model {
    public test_lab_model(String labusername, String labpassword) {
        this.labusername = labusername;
        this.labpassword = labpassword;
    }

    public test_lab_model() {

    }

    public String getLabusername() {
        return labusername;
    }

    public void setLabusername(String labusername) {
        this.labusername = labusername;
    }

    public String getLabpassword() {
        return labpassword;
    }

    public void setLabpassword(String labpassword) {
        this.labpassword = labpassword;
    }

    private String labusername;
    private String labpassword;
}
