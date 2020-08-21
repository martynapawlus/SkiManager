// This class is responsible for logging into users account

package com.example.skimanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private static String email1;
    Button btn_login, btn_registration;
    EditText txt_passwd_login, txt_email_login;

    public static String getEmail1() {
        return email1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login=findViewById(R.id.btn_login);
        txt_email_login=findViewById(R.id._txt_email_login);
        txt_passwd_login=findViewById(R.id._txt_haslo_login);
        btn_registration=findViewById(R.id.btn_rejestracja);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txt_email_login.getText().toString();
                String password = Base64.encodeToString(txt_passwd_login.getText().toString().getBytes(), Base64.DEFAULT);
                System.out.println(password);
                String type="login";
                // Connecting to the database and checking whether email and password are correct
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type, email, password);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // If password is correct
                if(backgroundTask.isResult1() == true) {
                    email1 = txt_email_login.getText().toString();
                    Intent intent = new Intent(Login.this, MainPanel.class);
                    startActivity(intent);
                }

            }
        });

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Changing view to registration panel
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}