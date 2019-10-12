package com.doc.docrepos.models;

public class doc_registration_model {
    public doc_registration_model(String demail) {
        this.demail = demail;
    }

    public String getDemail() {
        return demail;
    }

    public void setDemail(String demail) {
        this.demail = demail;
    }

    public  String demail;
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDusername() {
        return dusername;
    }

    public void setDusername(String dusername) {
        this.dusername = dusername;
    }

    public String getDpassword() {
        return dpassword;
    }

    public void setDpassword(String dpassword) {
        this.dpassword = dpassword;
    }

    public doc_registration_model(String dname, String dusername, String dpassword) {
        this.dname = dname;
        this.dusername = dusername;
        this.dpassword = dpassword;
    }

    private String dname;
    private String dusername;
    private String dpassword;

    public doc_registration_model() {
    }
}
