package com.example.readingmate;

import android.content.Context;
import android.content.SharedPreferences;

public class SignupPreferenceManager {

    private static final String PREFERENCE_NAME = "SignupPreferences";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    private SharedPreferences preferences;

    public SignupPreferenceManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserInfo(String userId, String password, String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getUserId() {
        return preferences.getString(KEY_USER_ID, "");
    }

    public String getPassword() {
        return preferences.getString(KEY_PASSWORD, "");
    }

    public String getEmail() {
        return preferences.getString(KEY_EMAIL, "");
    }
}
