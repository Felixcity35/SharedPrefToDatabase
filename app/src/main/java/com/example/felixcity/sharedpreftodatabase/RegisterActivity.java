package com.example.felixcity.sharedpreftodatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private DbHelper db;

    private TextView tvButt;
    private EditText editEmail,editPass;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DbHelper(this);

        editEmail=findViewById(R.id.editxtemail);
        editPass = findViewById(R.id.editxtpass);
        tvButt = findViewById(R.id.txtViewbutt);
        regButton =findViewById(R.id.regbutt);

        tvButt.setOnClickListener(this);
        regButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.regbutt :
                    register();
                break;
            case R.id.txtViewbutt :
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }

    private void register(){
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(getApplicationContext(),"email and passwrd field is empty ",Toast.LENGTH_LONG);
        }else
            db.addUser(email,password);
        Toast.makeText(getApplicationContext(),"Registered Successful ",Toast.LENGTH_LONG).show();
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }
}
