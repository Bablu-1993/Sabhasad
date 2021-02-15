package com.softwarez.technocrew.esabhsad;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPrefrence {
Context context;

    public LoginPrefrence(Context context) {
        this.context = context;
    }
    private SharedPreferences getLoginPrefrence(){
      return context.getSharedPreferences("com.softwarez.technocrew",Context.MODE_PRIVATE);
    }
    public void removeLoginPrefrence(){
          getLoginPrefrence().edit().clear().apply();
    }
    public Boolean isLoggedIn(){
            return getLoginPrefrence().getBoolean("loggedin",false);
    }
    public void setLoggedIn(boolean b){
        getLoginPrefrence().edit().putBoolean("loggedin",b).apply();
    }
    public String getAuthToken() {
        return getLoginPrefrence().getString("AUTH_TOKEN", "NA");
    }

    public void setAuthToken(String token) {
        getLoginPrefrence().edit().putString("AUTH_TOKEN", token).apply();
    }
}
