package org.openmrs.module.integration.api.model;

import java.util.Date;
public class Prescription {
    /**
     * Missing Indicator:Doctor's Licence
     * Replaced with Doctor's ID
     */
    private int order_id;// order_id:(Prescription Identifier, Prescription)
    private int order_oauth; //Prescription Authorization;
    private double Quantity;// Dose * Duration * Frequency: ( Prescription Quantity)
    private int concept_id; //concept_id: (Drug identifier)
    private String identifier; //identifier: (unique ID)
    private String PatientLastname;
    private String PatientFirstname;
    private String PatientSecondname;
    private int duration;
    private Date birthdate;
    private String PatientAddress;//city_village + county_district :(Patient Address)
    private String PatientCity;//state_province
    private Date scheduled_date;// Ingestion Date
    private int Doctorid;
    private String DoctorLastname;
    private String DoctorFirstname;
    private String DoctorSecondname;
    private  String order_action;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_oauth() {
        return order_oauth;
    }

    public void setOrder_oauth(int order_oauth) {
        this.order_oauth = order_oauth;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public int getConcept_id() {
        return concept_id;
    }

    public void setConcept_id(int concept_id) {
        this.concept_id = concept_id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPatientLastname() {
        return PatientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        PatientLastname = patientLastname;
    }

    public String getPatientFirstname() {
        return PatientFirstname;
    }

    public void setPatientFirstname(String patientFirstname) {
        PatientFirstname = patientFirstname;
    }

    public String getPatientSecondname() {
        return PatientSecondname;
    }

    public void setPatientSecondname(String patientSecondname) {
        PatientSecondname = patientSecondname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int d) {
       duration= d;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }

    public String getPatientCity() {
        return PatientCity;
    }

    public void setPatientCity(String patientCity) {
        PatientCity = patientCity;
    }

    public Date getScheduled_date() {
        return scheduled_date;
    }

    public void setScheduled_date(Date scheduled_date) {
        this.scheduled_date = scheduled_date;
    }

    public int getDoctorid() {
        return Doctorid;
    }

    public void setDoctorid(int doctorid) {
        Doctorid = doctorid;
    }

    public String getDoctorLastname() {
        return DoctorLastname;
    }

    public void setDoctorLastname(String doctorLastname) {
        DoctorLastname = doctorLastname;
    }

    public String getDoctorFirstname() {
        return DoctorFirstname;
    }

    public void setDoctorFirstname(String doctorFirstname) {
        DoctorFirstname = doctorFirstname;
    }

    public String getDoctorSecondname() {
        return DoctorSecondname;
    }

    public void setDoctorSecondname(String doctorSecondname) {
        DoctorSecondname = doctorSecondname;
    }

    public String getOrder_action() {
        return order_action;
    }

    public void setOrder_action(String order_action) {
        this.order_action = order_action;
    }
}
