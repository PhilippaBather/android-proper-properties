package com.philippabather.properproperties.domain;

/**
 * LoginResponse - maneja la respuesta de un login.
 */
public class LoginResponse {

    private String username;
    private String token;
    private boolean isLoggedIn;

    public LoginResponse(String username, String token) {
        this.username = username;
        this.token = token;
        this.isLoggedIn = true;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
