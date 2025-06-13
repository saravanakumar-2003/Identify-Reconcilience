package com.example.Identify.Reconcilience.DTO;


// Class for getting input from user in JSON format
public class ContactDTO {

    private String phoneNumber;
    private String email;

    public ContactDTO(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ContactDTO() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
