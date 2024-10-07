package com.example.userinterfaceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity<EmailPasswordActivity> extends AppCompatActivity {

    Button registerButton;
    EditText username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.usernameEditText);
        email = findViewById(R.id.emailEditTextRegister);
        password = findViewById(R.id.passwordEditTextRegister);
        registerButton = findViewById(R.id.signUpButton);

    }

    public void save (View view) {
        String uname = username.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        User usuario = new User(uname, mail, pass);
        if (uname.equals("") || mail.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(), "You must enter a value in each field", Toast.LENGTH_LONG).show();
        } else {
            List<User> user = User.find(User.class, "email='"+mail+"'", null);
            if(user.size()<=0) {
                usuario.save();
                username.setText("");
                email.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(), "User registered succesfully", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
            else {
                Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
            }
        }


    }

}