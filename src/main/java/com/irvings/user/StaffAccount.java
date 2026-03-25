package com.irvings.user;

import java.util.UUID;

public class StaffAccount {
    private final String staffId;
    private String name;
    private String email;
    private String role;
    private boolean active;

    public StaffAccount(String name, String email, String role) {
        this.staffId = "STF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = true;
        System.out.println("[StaffAccount] Created " + staffId + " (" + role + ")");
    }

    public String getStaffId() { return staffId; }
    public String getRole() { return role; }

    public void updateRole(String newRole) {
        System.out.println("[StaffAccount] updateRole: " + role + " -> " + newRole);
        this.role = newRole;
    }

    public boolean isActive() { return active; }

    public void deactivate() {
        System.out.println("[StaffAccount] deactivate for " + staffId);
        this.active = false;
    }

    public String getEmail() { return email; }
}

