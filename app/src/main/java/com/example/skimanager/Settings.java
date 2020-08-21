// This class is responsible for changing password

package com.example.skimanager;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    Button btn_change;
    EditText txt_newPassword, txt_oldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn_change = findViewById(R.id.btn_change);
        txt_newPassword = findViewById(R.id.txt_NewPassword);
        txt_oldPassword = findViewById(R.id.txt_oldPassword);

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updating new password to the database
                String oldPassword = Base64.encodeToString(txt_oldPassword.getText().toString().getBytes(), Base64.DEFAULT);
                String newPassword = Base64.encodeToString(txt_newPassword.getText().toString().getBytes(), Base64.DEFAULT);
                String type="password_change";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type, Login.getEmail1(), oldPassword, newPassword);
            }
        });
    }
}
