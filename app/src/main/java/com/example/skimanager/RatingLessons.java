// This class is responsible for rating past lessons

package com.example.skimanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RatingLessons extends AppCompatActivity {

    Button submit;
    Spinner spinner;
    RatingBar ratingBar;
    private String tmp_instructor, tmp_year, tmp_month, tmp_day, tmp_hour;
    private String[][] data_lesson, new_data_lesson;
    private int tmp_position;
    private ArrayList<String> lessons = new ArrayList<>();
    private ArrayList<Integer> newPosition = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_lessons);
        // Connecting to php file in order to get information about past lessons
        String type="lesson_info";
        BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
        backgroundTask.execute(type, Login.getEmail1());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        submit = findViewById(R.id.btn_submit);
        spinner = findViewById(R.id.spinner_lessons);
        ratingBar = findViewById(R.id.ratingBar);
        data_lesson = backgroundTask.getData_lesson();

        // Creating readable date including 0 before first to 9th day of month
        for(int i = 0; i < data_lesson[0].length; i++){
            String compareDate;
            if(Integer.parseInt(data_lesson[3][i]) < 10) {
                if(Integer.parseInt(data_lesson[2][i]) <10) {
                    compareDate = "0" + data_lesson[3][i] + ".0" + data_lesson[2][i] + "." + data_lesson[1][i];
                } else {
                    compareDate = "0" + data_lesson[3][i] + "." + data_lesson[2][i] + "." + data_lesson[1][i];
                }
            } else {
                if(Integer.parseInt(data_lesson[2][i]) <10) {
                    compareDate = data_lesson[3][i] + ".0" + data_lesson[2][i] + "." + data_lesson[1][i];
                } else {
                    compareDate = data_lesson[3][i] + "." + data_lesson[2][i] + "." + data_lesson[1][i];
                }
            }

            // Comparing today's date and date of lesson
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String now = dateFormat.format(date);
            Integer nowInt = Integer.parseInt(now.substring(0,2)) + Integer.parseInt(now.substring(3,5))*100 + Integer.parseInt(now.substring(6))*10000;
            Integer dateInt = Integer.parseInt(compareDate.substring(0,2)) + Integer.parseInt(compareDate.substring(3,5))*100 + Integer.parseInt(compareDate.substring(6))*10000;

            if(nowInt > dateInt) {
                String lesson = "Instruktor: " + data_lesson[0][i] + " , " + data_lesson[4][i] + ":00" + " " + compareDate;
                lessons.add(lesson);
                newPosition.add(i);
            }
        }
        if(lessons.isEmpty()) {
            lessons.add("Nie ma zadnych lekcji do oceny");
        }
        ArrayAdapter<String> adapter_lessons = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lessons);
        adapter_lessons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_lessons);
        if(lessons.get(0).equals("Nie ma zadnych lekcji do oceny")) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            // If there are past lessons that aren't rated yet
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tmp_instructor = data_lesson[0][newPosition.get(position)];
                    tmp_year = data_lesson[1][newPosition.get(position)];
                    tmp_month = data_lesson[2][newPosition.get(position)];
                    tmp_day = data_lesson[3][newPosition.get(position)];
                    tmp_hour = data_lesson[4][newPosition.get(position)];
                    tmp_position = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updating the rate of past lesson
                String type1="lesson_rate";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                backgroundTask.execute(type1, Login.getEmail1(), tmp_instructor, tmp_year, tmp_month, tmp_day, tmp_hour, Float.toString(ratingBar.getRating()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adapter_lessons.remove(lessons.get(tmp_position));
            }
        });
    }
}
