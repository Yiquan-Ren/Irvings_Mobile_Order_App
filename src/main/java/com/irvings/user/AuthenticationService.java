package com.irvings.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationService {

    private final Map<String, String> credentialStore = new HashMap<>();
    private final Map<String, String> sessions = new HashMap<>(); // sessionId -> email

    public AuthenticationService() {
        // deterministic credentials for testharness
        credentialStore.put("test@psu.edu", "password");
        credentialStore.put("staff@irvings.com", "staffpass");
        System.out.println("[AuthenticationService] Stub credentials loaded");
    }

    public boolean login(String email, String password) {
        System.out.println("[AuthenticationService] login(" + email + ")");
        String expected = credentialStore.get(email);
        return expected != null && expected.equals(password);
    }

    public String createSession(String email, String password) {
        System.out.println("[AuthenticationService] createSession(" + email + ")");
        if (!login(email, password)) return null;
        String sessionId = "SES-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
        sessions.put(sessionId, email);
        return sessionId;
    }

    public boolean validateSession(String sessionId) {
        System.out.println("[AuthenticationService] validateSession(" + sessionId + ")");
        return sessions.containsKey(sessionId);
    }

    public void logout(String sessionId) {
        System.out.println("[AuthenticationService] logout(" + sessionId + ")");
        sessions.remove(sessionId);
    }

    public boolean register(String email, String password) {
        System.out.println("[AuthenticationService] register(" + email + ")");
        if (credentialStore.containsKey(email)) return false;
        credentialStore.put(email, password);
        return true;
    }
}
