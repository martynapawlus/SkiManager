package com.example.skimanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FutureLessons extends AppCompatActivity {

    Button cancel;
    Spinner spinner;
    private String[][] data_lesson;
    private ArrayList<Integer> newPosition = new ArrayList<>();
    private ArrayList<String> lessons = new ArrayList<>();
    private String tmp_instructor, tmp_year, tmp_month, tmp_day, tmp_hour;
    private int tmp_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_lessons);

        String type="lesson_info";
        BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
        backgroundTask.execute(type, login.getEmail1());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cancel = findViewById(R.id.btn_cancel);
        spinner = findViewById(R.id.spinner_cancel);
        data_lesson = backgroundTask.getData_lesson();
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
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String now = dateFormat.format(date);
            Integer nowInt = Integer.parseInt(now.substring(0,2)) + Integer.parseInt(now.substring(3,5))*100 + Integer.parseInt(now.substring(6))*10000;
            Integer dateInt = Integer.parseInt(compareDate.substring(0,2)) + Integer.parseInt(compareDate.substring(3,5))*100 + Integer.parseInt(compareDate.substring(6))*10000;

            if(nowInt < dateInt) {
                String lesson = "Instruktor: " + data_lesson[0][i] + " , " + data_lesson[4][i] + ":00" + " " + compareDate;
                lessons.add(lesson);
                newPosition.add(i);
            }
        }
        if(lessons.isEmpty()) {
            lessons.add("Nie masz żadnych nadchodzących lekcji");
        }
        for(int i = 0; i < lessons.size(); i++){
            System.out.println(lessons.get(i));
        }
        ArrayAdapter<String> adapter_lessons = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lessons);
        adapter_lessons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_lessons);
        if(lessons.get(0).equals("Nie masz żadnych nadchodzących lekcji")) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
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
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type1="lesson_cancel";
                BackgroundTask backgroundTask= new BackgroundTask(getApplicationContext());
                System.out.println(login.getEmail1() + tmp_instructor + tmp_year + tmp_month + tmp_day + tmp_hour);
                backgroundTask.execute(type1, login.getEmail1(), tmp_instructor, tmp_year, tmp_month, tmp_day, tmp_hour);
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
