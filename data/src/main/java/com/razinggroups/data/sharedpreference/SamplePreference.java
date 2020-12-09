package com.razinggroups.data.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SamplePreference {


    private static final String USER_PREFERENCES = "user_preferences";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMPLOYEE_ID = "employee_id";

    private SharedPreferences userSharedPreferences;
    private SharedPreferences.Editor editor;

    public SamplePreference(Context context) {
        if (userSharedPreferences == null)
            userSharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Activity.MODE_PRIVATE);
    }

    public String getUserName() {
        return this.userSharedPreferences.getString(USERNAME, "");
    }

    public String getPassword() {
        return this.userSharedPreferences.getString(PASSWORD, "");
    }

    public long getEmployeeId() {
        return this.userSharedPreferences.getLong(EMPLOYEE_ID, 0);
    }


    public SamplePreference edit() {
        editor = this.userSharedPreferences.edit();
        return this;
    }

    public SamplePreference putUserName(String username) {
        this.editor.putString(USERNAME, username);
        return this;
    }

    public SamplePreference putPassword(String password) {
        this.editor.putString(PASSWORD, password);
        return this;
    }

    public SamplePreference putEmployeeId(long id) {
        this.editor.putLong(EMPLOYEE_ID, id);
        return this;
    }

    public void apply() {
        this.editor.apply();
        this.editor = null;
    }

    public SharedPreferences getPreferences() {
        return this.userSharedPreferences;
    }


}
