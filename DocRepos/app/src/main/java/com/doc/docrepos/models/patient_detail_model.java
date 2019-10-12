package com.doc.docrepos.models;

public class patient_detail_model {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public patient_detail_model(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private String age;

    public patient_detail_model(String symptoms_model, String disease_model, String medicines_model) {
        this.symptoms_model = symptoms_model;
        this.disease_model = disease_model;
        this.medicines_model = medicines_model;
    }

    public String getSymptoms_model() {
        return symptoms_model;
    }

    public void setSymptoms_model(String symptoms_model) {
        this.symptoms_model = symptoms_model;
    }

    public String getDisease_model() {
        return disease_model;
    }

    public void setDisease_model(String disease_model) {
        this.disease_model = disease_model;
    }

    public String getMedicines_model() {
        return medicines_model;
    }

    public void setMedicines_model(String medicines_model) {
        this.medicines_model = medicines_model;
    }

    private String symptoms_model;
    private String disease_model;
    private String medicines_model;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChrodiseases() {
        return chrodiseases;
    }

    public void setChrodiseases(String chrodiseases) {
        this.chrodiseases = chrodiseases;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public patient_detail_model() {
    }

    public patient_detail_model(String id, String name, String age, String gender, String chrodiseases, String weight, String date) {
        this.id = id;
        this.name = name;
        this.date=date;
        this.age = age;
        this.gender = gender;
        this.chrodiseases = chrodiseases;
        this.weight = weight;
    }

    private String gender;
    private String chrodiseases;
    private String weight;

}
