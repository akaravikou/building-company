package com.solvd.buildingcompany.domain;

import java.time.LocalDateTime;

public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private LocalDateTime dateOfBirth;
    private PaySheet paySheet;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PaySheet getPaySheet() {
        return paySheet;
    }

    public void setPaySheet(PaySheet paySheet) {
        this.paySheet = paySheet;
    }
}
