package com.solvd.buildingcompany.domain;

public class PaySheet {

    private Long id;
    private Integer personnelNumber;
    private Integer hoursWorked;
    private Integer paymentRublesPerHour;

    public PaySheet(Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Integer getPaymentRublesPerHour() {
        return paymentRublesPerHour;
    }

    public void setPaymentRublesPerHour(Integer paymentRublesPerHour) {
        this.paymentRublesPerHour = paymentRublesPerHour;
    }
}
