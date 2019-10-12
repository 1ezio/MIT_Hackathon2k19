package com.doc.docrepos.models;

public class test_lab_report_model {
    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_img_uri() {
        return test_img_uri;
    }

    public void setTest_img_uri(String test_img_uri) {
        this.test_img_uri = test_img_uri;
    }

    public test_lab_report_model(String test_name, String test_img_uri) {
        this.test_name = test_name;
        this.test_img_uri = test_img_uri;
    }

    private String test_name;
    private String test_img_uri;

    public test_lab_report_model() {
    }
}
