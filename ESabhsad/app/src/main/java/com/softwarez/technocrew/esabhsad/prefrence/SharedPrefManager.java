package com.softwarez.technocrew.esabhsad.prefrence;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_MOBILE="mobile";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_ID = "keyid";
    private static SharedPrefManager mInstance;
    private static Context context;

    private SharedPrefManager(Context context){
        this.context=context;
    }
    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MOBILE,"admin_mobile");
        editor.apply();
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public void getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                sharedPreferences.getString(KEY_MOBILE, null);

    }
}
