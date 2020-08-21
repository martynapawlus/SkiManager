// This class is responsible for registration

package com.example.skimanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button _btnReg, _btnLogin;
    EditText _txtName, _txtSurname, _txtBirthday, _txtPhone, _txtEmail, _txtPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(Base64.encodeToString("test".getBytes(), Base64.DEFAULT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnLogin=(Button)findViewById(R.id.login_btn);
        _btnReg=(Button)findViewById(R.id.rejestracja_btn);
        _txtName =(EditText)findViewById(R.id.txt_imie);
        _txtSurname =(EditText)findViewById(R.id.txt_nazwisko);
        _txtBirthday =(EditText)findViewById(R.id.txt_urodziny);
        _txtPhone =(EditText)findViewById(R.id.txt_telefon);
        _txtEmail=(EditText)findViewById(R.id.txt_email);
        _txtPasswd =(EditText)findViewById(R.id.txt_haslo);
        _btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name= _txtName.getText().toString();
                String surname= _txtSurname.getText().toString();
                String birthday= _txtBirthday.getText().toString();
                String phone= _txtPhone.getText().toString();
                String email=_txtEmail.getText().toString();
                // Password is hold as a hash in the database for obviously known reasons ;)
                String password = Base64.encodeToString(_txtPasswd.getText().toString().getBytes(), Base64.DEFAULT);
                // Connecting to the database and inserting new account
                String type="reg";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type, first_name, surname, birthday, phone, email, password);

            }
        });
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }

}
