package com.philippabather.properproperties.domain;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefEditor;
    private final String SHARED_PREF_NAME = "session";
    private final String USER_ID = "user_id";
    private final String USER_LOGGED_IN = "user_logged_in";
    private final String USER_TOKEN = "user_token";
    private final String USER_USERNAME = "user_username";

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        prefEditor = sharedPreferences.edit();
    }

    public void saveSession(LoginResponse loginResponse) {
        setUsername(loginResponse.getUsername());
        setToken(loginResponse.getToken());
        setLoggedIn(loginResponse.isLoggedIn());
    }

    public void deleteSession() {
        setToken(null);
        setUsername(null);
        setLoggedIn(false);
        setUserId(-1);
    }

    public void setUserId(long id) {
        prefEditor.putLong(USER_ID, id).commit();
    }

    public long getUserId() {
        return sharedPreferences.getLong(USER_ID, -1);
    }
    private void setLoggedIn(boolean loggedIn) {
        prefEditor.putBoolean(USER_LOGGED_IN, loggedIn).commit();
    }

    private void setToken(String token) {
        prefEditor.putString(USER_TOKEN, token).commit();
    }

    public String getToken() {
        return sharedPreferences.getString(USER_TOKEN, null);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);
    }

    private void setUsername(String username) {
        prefEditor.putString(USER_USERNAME, username).commit();
    }

    public String getUsername() {
        return sharedPreferences.getString(USER_USERNAME, null);
    }

}
