package com.example.demo.entity;

public class NnhStudent {
    Long nnhId;
    String nnhName;
    int nnhAge;
    String nnhGender;
    String nnhAddress;
    String nnhPhone;
    String nnhEmail;


    public NnhStudent() {
    }

    public NnhStudent(Long nnhId, String nnhName, int nnhAge, String nnhGender,
                      String nnhAddress, String nnhPhone, String nnhEmail) {
        this.nnhId = nnhId;
        this.nnhName = nnhName;
        this.nnhAge = nnhAge;
        this.nnhGender = nnhGender;
        this.nnhAddress = nnhAddress;
        this.nnhPhone = nnhPhone;
        this.nnhEmail = nnhEmail;
    }

    public Long getNnhId() {
        return nnhId;
    }

    public void setNnhId(Long nnhId) {
        this.nnhId = nnhId;
    }

    public String getNnhName() {
        return nnhName;
    }

    public void setNnhName(String nnhName) {
        this.nnhName = nnhName;
    }

    public int getNnhAge() {
        return nnhAge;
    }

    public void setNnhAge(int nnhAge) {
        this.nnhAge = nnhAge;
    }

    public String getNnhGender() {
        return nnhGender;
    }

    public void setNnhGender(String nnhGender) {
        this.nnhGender = nnhGender;
    }

    public String getNnhAddress() {
        return nnhAddress;
    }

    public void setNnhAddress(String nnhAddress) {
        this.nnhAddress = nnhAddress;
    }

    public String getNnhPhone() {
        return nnhPhone;
    }

    public void setNnhPhone(String nnhPhone) {
        this.nnhPhone = nnhPhone;
    }

    public String getNnhEmail() {
        return nnhEmail;
    }

    public void setNnhEmail(String nnhEmail) {
        this.nnhEmail = nnhEmail;
    }

    @Override
    public String toString() {
        return "NnhStudent{" +
                "nnhId=" + nnhId +
                ", nnhName='" + nnhName + '\'' +
                ", nnhAge=" + nnhAge +
                ", nnhGender='" + nnhGender + '\'' +
                ", nnhAddress='" + nnhAddress + '\'' +
                ", nnhPhone='" + nnhPhone + '\'' +
                ", nnhEmail='" + nnhEmail + '\'' +
                '}';
    }
}