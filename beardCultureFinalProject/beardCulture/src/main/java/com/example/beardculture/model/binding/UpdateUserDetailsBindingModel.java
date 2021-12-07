package com.example.beardculture.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateUserDetailsBindingModel {

    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public UpdateUserDetailsBindingModel() {
    }

    @NotBlank
    @Size(min = 2, max = 20)
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

    @Size(min = 10,max = 13)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotBlank
    @Size(min = 3)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
