// This class is responsible for choosing name of instructor and the date when the lesson will be taken

package com.example.skimanager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InstructorChoice extends AppCompatActivity {

    private static String tmp_instructor;
    private static String tmp_year;
    private static String tmp_month;
    private static String tmp_day;
    CalendarView calendarView;
    Spinner spinner;
    Button button;

    public static String getTmp_instructor() {
        return tmp_instructor;
    }

    public static String getTmp_year() {
        return tmp_year;
    }

    public static String getTmp_month() {
        return tmp_month;
    }

    public static String getTmp_day() {
        return tmp_day;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_choice);

        tmp_year = Integer.toString(java.time.LocalDate.now().getYear());
        tmp_month = Integer.toString(java.time.LocalDate.now().getMonthValue());
        tmp_day = Integer.toString(java.time.LocalDate.now().getDayOfMonth());
        tmp_instructor = "Anna";

        spinner = findViewById(R.id.spinner);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        button = findViewById(R.id.btn_zatwierdz);
        ArrayList<String> instructors = new ArrayList<String>();
        // Instructors names
        instructors.add("Anna");
        instructors.add("Dominika");
        instructors.add("Mateusz");
        instructors.add("Grzegorz");
        instructors.add("Szymon");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, instructors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Selected instructor
                tmp_instructor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Selected date
                tmp_year= String.valueOf(year);
                tmp_month=String.valueOf(month+1);
                tmp_day=String.valueOf(dayOfMonth);
                System.out.println(tmp_instructor + " " + tmp_year + " " + tmp_month + " " + tmp_day);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Connecting to the server and confirming date and instructor
                String type="instructor";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type, tmp_instructor, tmp_year, tmp_month, tmp_day);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(InstructorChoice.this, HourChoice.class);
                startActivity(intent);
            }
        });

    }
}
