package com.example.felixcity.sharedpreftodatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logoutButt;
    private  Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         session = new Session(this);
         if(!session.loggedin()){
             Logout();
         }
        logoutButt = findViewById(R.id.buttLogout);
         logoutButt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Logout();
             }
         });
    }

private void Logout(){
        session.setLoggedin(false);
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    finish();
}
}
