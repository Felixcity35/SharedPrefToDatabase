package com.example.felixcity.sharedpreftodatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editemail,editpassword;
    private Button loginButton,registerButton;

    private Session session;

    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session  = new Session(this);
        editemail=findViewById(R.id.editextemail);
        editpassword=findViewById(R.id.editextPass);
        loginButton = findViewById(R.id.loginButt);
        registerButton=findViewById(R.id.registerButt);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButt :
                      Login();

                break;

            case R.id.registerButt :
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

        }
    }

    private void Login(){
        String email = editemail.getText().toString();
        String pass = editpassword.getText().toString();

        if( db.getUser(email,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();

          //  Toast.makeText(getApplicationContext(),"Login Successful ",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(getApplicationContext(),"Wrong Email or Password ",Toast.LENGTH_LONG).show();
    }
}
