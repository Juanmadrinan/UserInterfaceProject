package com.example.userinterfaceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin1;

    EditText email1, pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin1 = findViewById(R.id.loginButton);
        email1 = findViewById(R.id.emailEditTextLogin);
        pass1 = findViewById(R.id.passwordEditTextLogin);
    }

    public void onClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view){

        String email = email1.getText().toString();
        String pass = pass1.getText().toString();

        if(email.equals("") || pass.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Error, Data required ", Toast.LENGTH_LONG).show();
        }else{
            List<User> userFinded = User.find(User.class, "email='" + email + "' AND password= '" + pass + "'", null);
            if( userFinded.size() <= 0){
                Toast.makeText(getApplicationContext(), "Email/Password INCORRECT", Toast.LENGTH_LONG).show();
            }else{
                email1.setText("");
                pass1.setText("");
                Toast.makeText(getApplicationContext(), "WELCOME!", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intenL = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intenL);
                    }
                }, 700);
            }
        }
    }
}