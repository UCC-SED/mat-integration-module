package org.openmrs.module.integration.api.model;

public class Registration {
    private String Mat_id;
    private String given_name;
    private String middle_name;
    private String family_name;
    private double age;
    private String gender;

    public String getMat_id() {
        return Mat_id;
    }

    public void setMat_id(String mat_id) {
        Mat_id = mat_id;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public double getAge() {
        return ((int) age);
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
