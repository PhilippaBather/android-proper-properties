package com.philippabather.properproperties.domain;

/**
 * LoginRequest - maneja un login request
 *
 * @author Philippa Bather
 */
public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
