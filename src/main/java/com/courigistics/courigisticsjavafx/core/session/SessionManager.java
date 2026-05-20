package com.courigistics.courigisticsjavafx.core.session;

import lombok.Getter;

/**
 * Singleton managing the current user session and security state.
 * Responsibility: Storing the JWT token, current user details, and managing session persistence/clearing on logout.
 */
@Getter
public class SessionManager {
    private static SessionManager instance;

    private String accessToken;
    private String refreshToken;
    private String username;
    private String email;
    private String role; // "CUSTOMER", "COURIER", "ADMIN"

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void saveSession(String accessToken, String refreshToken,
                            String username, String email, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public void clearSession() {
        this.accessToken = null;
        this.refreshToken = null;
        this.username = null;
        this.email = null;
        this.role = null;
    }

    public boolean isLoggedIn() {
        return accessToken != null && !accessToken.isBlank();
    }

    public String getBearerToken() {
        return "Bearer " + accessToken;
    }

    // Getters
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    public boolean isCustomer() { return "CUSTOMER".equals(role); }
    public boolean isCourier() { return "COURIER".equals(role); }
    public boolean isAdmin() { return "ADMIN".equals(role); }
}
