// This class is responsible for choosing when and where the lesson will be taken

package com.example.skimanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HourChoice extends AppCompatActivity {

    Button btn_submit;
    Spinner spinner_place, spinner_quantity, spinner_hour;
    TextView textView;
    private String tmp_place, tmp_quantity, tmp_hour;
    private String[][] data;
    private ArrayList<String> hour = new ArrayList<String>();

    // Method called each time user change choices about an hour
    public void refresh() {
        hour.clear();
        if (tmp_place.equals("Ośla Łączka")) {
            switch (tmp_quantity) {
                case "1":
                    for (int i = 0; i < 12; i++) {
                        if (data[1][i].equals("t")) {
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
                case "2":
                    for (int i = 0; i < 11; i++) {
                        if (data[1][i].equals("t") && data[1][i + 1].equals("t")) {
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
                case "3":
                    for (int i = 0; i < 10; i++) {
                        if (data[1][i].equals("t") && data[1][i + 1].equals("t") && data[1][i + 2].equals("t")) {
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
            }
        } else if (tmp_place.equals("Duży Stok")) {
            switch (tmp_quantity) {
                case "1":
                    for (int i = 0; i < 12; i++) {
                        if (data[1][i].equals("t")) {
                            if (data[0][i].equals("16") || data[0][i].equals("17")) {
                                continue;
                            }
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
                case "2":
                    for (int i = 0; i < 11; i++) {
                        if (data[1][i].equals("t") && data[1][i + 1].equals("t")) {
                            if (data[0][i].equals("15") || data[0][i].equals("16") || data[0][i].equals("17")) {
                                continue;
                            }
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
                case "3":
                    for (int i = 0; i < 10; i++) {
                        if (data[1][i].equals("t") && data[1][i + 1].equals("t") && data[1][i + 2].equals("t")) {
                            if (data[0][i].equals("14") || data[0][i].equals("15") || data[0][i].equals("16") || data[0][i].equals("17")) {
                                continue;
                            }
                            String h = data[0][i];
                            h = h + ":00";
                            hour.add(h);
                        }
                    }
                    break;
            }
        }
        tmp_hour = hour.get(0);
    }

    // This method writes nicely where and when the lesson will take place just for confirmation
    public void refreshText() {
        System.out.println("odswiezamy");
        String u = Integer.toString(Integer.parseInt(tmp_hour.substring(0,tmp_hour.indexOf(":"))) + Integer.parseInt(tmp_quantity)) + ":00";
        textView.setText(tmp_place + " od " + tmp_hour + " do " + u);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tmp_place = "Ośla Łączka";
        tmp_quantity = "1";
        tmp_hour = "8:00";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_choice);
        data = BackgroundTask.getData();
        btn_submit = findViewById(R.id.btn_appr);
        spinner_place = findViewById(R.id.spinner2);
        spinner_quantity = findViewById(R.id.spinner3);
        spinner_hour = findViewById(R.id.spinner4);
        textView = findViewById(R.id.text_ap);

        ArrayList<String> places = new ArrayList<String>();
        places.add("Ośla Łączka");
        places.add("Duży Stok");
        ArrayAdapter<String> adapter_place = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, places);
        adapter_place.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_place.setAdapter(adapter_place);
        spinner_place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Every time user choose different place
                tmp_place = parent.getItemAtPosition(position).toString();
                refresh();
                refreshText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> quantity = new ArrayList<String>();
        quantity.add("1");
        quantity.add("2");
        quantity.add("3");
        ArrayAdapter<String> adapter_quantity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantity);
        adapter_quantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_quantity.setAdapter(adapter_quantity);
        spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Every time user choose different number of hours
                tmp_quantity = parent.getItemAtPosition(position).toString();
                refresh();
                refreshText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i = 0; i < 12; i++) {
            if (data[1][i].equals("t")) {
                String h = data[0][i];
                h = h + ":00";
                hour.add(h);
            }
        }
        tmp_hour = hour.get(0);
        ArrayAdapter<String> adapter_hour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hour);
        adapter_hour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hour.setAdapter(adapter_hour);
        spinner_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Every time user choose different time
                tmp_hour = parent.getItemAtPosition(position).toString();
                refreshText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Connecting to database and confirming the lesson
                String type="lesson";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type, InstructorChoice.getTmp_instructor(), InstructorChoice.getTmp_year(), InstructorChoice.getTmp_month(), InstructorChoice.getTmp_day(), tmp_hour.substring(0,tmp_hour.indexOf(":")), tmp_quantity, Login.getEmail1());
            }
        });

    }
}
