package com.example.felixcity.sharedpreftodatabase;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("my sharedpref", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLoggedin(Boolean loggedin) {
        editor.putBoolean("Loggedin Mode ", loggedin);
        editor.commit();
    }


    public boolean loggedin(){
        return pref.getBoolean("Loggin Mode ",false);
    }

}
