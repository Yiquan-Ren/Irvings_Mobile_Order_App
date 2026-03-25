package com.irvings.user;

import java.util.UUID;

public class CustomerAccount {
    private final String customerId;
    private String fullName;
    private String email;
    private String phone;
    private boolean active;

    public CustomerAccount(String fullName, String email, String phone) {
        this.customerId = "CUS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.active = true;
        System.out.println("[CustomerAccount] Created " + customerId + " for " + email);
    }

    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }

    public void updateFullName(String newName) {
        System.out.println("[CustomerAccount] updateFullName: " + fullName + " -> " + newName);
        this.fullName = newName;
    }

    public String getEmail() { return email; }

    public void updateEmail(String newEmail) {
        System.out.println("[CustomerAccount] updateEmail: " + email + " -> " + newEmail);
        this.email = newEmail;
    }

    public String getPhone() { return phone; }

    public void updatePhone(String newPhone) {
        System.out.println("[CustomerAccount] updatePhone: " + phone + " -> " + newPhone);
        this.phone = newPhone;
    }

    public boolean isActive() { return active; }

    public void deactivateAccount() {
        System.out.println("[CustomerAccount] deactivateAccount for " + customerId);
        this.active = false;
    }
}
