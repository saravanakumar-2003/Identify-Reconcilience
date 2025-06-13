package com.example.Identify.Reconcilience.DTO;
import java.util.List;

//Class for return type
public class ReturnTypeDTO {

    private int primaryContactId;
    private List<String> emails;            //List of emails
    private List<String> phoneNumbers;      //List of phone numbers
    private List<Integer> number;           //List of IDs of secondary contacts

    public int getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(int primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Integer> getNumber() {
        return number;
    }

    public void setNumber(List<Integer> number) {
        this.number = number;
    }

    public ReturnTypeDTO() {
    }

    public ReturnTypeDTO(int primaryContactId, List<String> emails, List<String> phoneNumbers, List<Integer> number) {
        this.primaryContactId = primaryContactId;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.number = number;
    }
}
