package com.doc.docrepos.models;


public class data_upload_model {
    public data_upload_model(String tests_req) {
        this.tests_req = tests_req;
    }

    public String getTests_req() {
        return tests_req;
    }

    public void setTests_req(String tests_req) {
        this.tests_req = tests_req;
    }

    private String tests_req;
    private String AGE_input;

    public String getAGE_input() {
        return AGE_input;
    }

    public void setAGE_input(String AGE_input) {
        this.AGE_input = AGE_input;
    }

    public String getChronic() {
        return chronic;
    }

    public void setChronic(String chronic) {
        this.chronic = chronic;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private String chronic;

    public data_upload_model(String AGE_input, String chronic, String weight, String sym, String dos, String dis, String medi) {
        this.AGE_input = AGE_input;
        this.chronic = chronic;
        this.weight = weight;
        this.sym = sym;
        this.dos = dos;
        this.dis = dis;
        this.medi = medi;
    }

    private String weight;

    private String sym;

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }


    private String dos;

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getMedi() {
        return medi;
    }

    public void setMedi(String medi) {
        this.medi = medi;
    }

    public data_upload_model() {
    }



    private String dis;
    private String medi;
}
