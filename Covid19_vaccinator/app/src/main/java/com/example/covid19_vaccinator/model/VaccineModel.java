package com.example.covid19_vaccinator.model;

public class VaccineModel {

    private String vaccineCenter;
    private String vaccinationCharges;
    private String vaccineAge;
    private String vaccineTimings;
    private String vaccineName;
    private String vaccinationCenterAddress;
    private String vaccineAvailable;
    private String vaccineCenterTimings;

    public VaccineModel() {
    }

    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public String getVaccineAge() {
        return vaccineAge;
    }

    public void setVaccineAge(String vaccineAge) {
        this.vaccineAge = vaccineAge;
    }

    public String getVaccineTimings() {
        return vaccineTimings;
    }

    public void setVaccineTimings(String vaccineTimings) {
        this.vaccineTimings = vaccineTimings;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccinationCenterAddress() {
        return vaccinationCenterAddress;
    }

    public void setVaccinationCenterAddress(String vaccinationCenterAddress) {
        this.vaccinationCenterAddress = vaccinationCenterAddress;
    }

    public String getVaccineAvailable() {
        return vaccineAvailable;
    }

    public void setVaccineAvailable(String vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
    }

    public String getVaccineCenterTimings() {
        return vaccineCenterTimings;
    }

    public void setVaccineCenterTimings(String vaccineCenterTimings) {
        this.vaccineCenterTimings = vaccineCenterTimings;
    }

    public VaccineModel(String vaccineCenter, String vaccinationCharges, String vaccineAge, String vaccineTimings, String vaccineName, String vaccinationCenterAddress, String vaccineAvailable, String vaccineCenterTimings) {
        this.vaccineCenter = vaccineCenter;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccineAge = vaccineAge;
        this.vaccineTimings = vaccineTimings;
        this.vaccineName = vaccineName;
        this.vaccinationCenterAddress = vaccinationCenterAddress;
        this.vaccineAvailable = vaccineAvailable;
        this.vaccineCenterTimings = vaccineCenterTimings;
    }
}
