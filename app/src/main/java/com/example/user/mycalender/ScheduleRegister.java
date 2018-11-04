package com.example.user.mycalender;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ScheduleRegister extends AppCompatActivity {
    myDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_register);
        database = myDatabase.getInstance();//싱글톤 생성
    }

    public void GoCancelClick(View V){
        Toast.makeText(getApplicationContext(),"취소합니다.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),CalenderActivity.class);
        startActivity(intent);
    }
    public void GoRegistClick(View V) {
        EditText finishmemo = (EditText) findViewById(R.id.EditText);
        Spinner monthSpin = (Spinner)findViewById(R.id.SpinnerMonth);
        Spinner dateSpin = (Spinner)findViewById(R.id.SpinnerDay);
        String context = finishmemo.getText().toString();
        String month = monthSpin.getSelectedItem().toString();
        String date = dateSpin.getSelectedItem().toString();
        if((month.equals("2") && (date.equals("31")||date.equals("30")||date.equals("29"))) || ((month.equals("4")||month.equals("6")||month.equals("9")||month.equals("11"))&&date.equals("31"))){
            Toast.makeText(getApplicationContext(), "적절하지 않은 날짜가 등록되었습니다. 취소합니다.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
            startActivity(intent);
        }//예외처리
        else {
            Toast.makeText(getApplicationContext(), "등록되었습니다", Toast.LENGTH_LONG).show();
            if(context == "") database.initData(Integer.parseInt(month),Integer.parseInt(date),null);
            else database.initData(Integer.parseInt(month),Integer.parseInt(date),context);//data 저장.
            Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
            startActivity(intent);
        }
    }
}
