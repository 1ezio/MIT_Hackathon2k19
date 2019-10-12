package com.doc.docrepos.models;

public class patient_record_show {
    private String dates;
    public String getMedicine_repo_2() {
        return medicine_repo_2;
    }

    public void setMedicine_repo_2(String medicine_repo_2) {
        this.medicine_repo_2 = medicine_repo_2;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    private String medicine_repo_2;
    private String rec_id_m;

    public patient_record_show(String rec_id_m,String dates, String rec_name_m, String rec_age_m, String rec_gender_m, String rec_chro_m_, String rec_weight_m, String rec_sym_m, String rec_dis_m, String rec_medicine,String medicine_repo_2) {
        this.rec_id_m = rec_id_m;
        this.dates=dates;
        this.rec_name_m = rec_name_m;
        this.rec_age_m = rec_age_m;
        this.rec_gender_m = rec_gender_m;
        this.rec_chro_m_ = rec_chro_m_;
        this.rec_weight_m = rec_weight_m;
        this.rec_sym_m = rec_sym_m;
        this.rec_dis_m = rec_dis_m;
        this.rec_medicine = rec_medicine;
        this.medicine_repo_2= medicine_repo_2;
    }

    private String rec_name_m;
    private String rec_age_m;
    private String rec_gender_m;
    private String rec_chro_m_;
    private String rec_weight_m;
    private String rec_sym_m;
    private String rec_dis_m;
    private String rec_medicine;

    public patient_record_show() {
    }

    public String getRec_id_m() {
        return rec_id_m;
    }

    public void setRec_id_m(String rec_id_m) {
        this.rec_id_m = rec_id_m;
    }

    public String getRec_name_m() {
        return rec_name_m;
    }

    public void setRec_name_m(String rec_name_m) {
        this.rec_name_m = rec_name_m;
    }

    public String getRec_age_m() {
        return rec_age_m;
    }

    public void setRec_age_m(String rec_age_m) {
        this.rec_age_m = rec_age_m;
    }

    public String getRec_gender_m() {
        return rec_gender_m;
    }

    public void setRec_gender_m(String rec_gender_m) {
        this.rec_gender_m = rec_gender_m;
    }

    public String getRec_chro_m_() {
        return rec_chro_m_;
    }

    public void setRec_chro_m_(String rec_chro_m_) {
        this.rec_chro_m_ = rec_chro_m_;
    }

    public String getRec_weight_m() {
        return rec_weight_m;
    }

    public void setRec_weight_m(String rec_weight_m) {
        this.rec_weight_m = rec_weight_m;
    }

    public String getRec_sym_m() {
        return rec_sym_m;
    }

    public void setRec_sym_m(String rec_sym_m) {
        this.rec_sym_m = rec_sym_m;
    }

    public String getRec_dis_m() {
        return rec_dis_m;
    }

    public void setRec_dis_m(String rec_dis_m) {
        this.rec_dis_m = rec_dis_m;
    }

    public String getRec_medicine() {
        return rec_medicine;
    }

    public void setRec_medicine(String rec_medicine) {
        this.rec_medicine = rec_medicine;
    }
}
