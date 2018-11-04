package com.example.user.mycalender;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.Calendar;



public class CalenderActivity extends AppCompatActivity {
    myDatabase database;
    Toolbar toolbar;
    myCalendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        Calendar c = Calendar.getInstance();
        cal = new myCalendar();
        database = myDatabase.getInstance();//싱글톤 생성
        setMonthText();
        setDayText();
        setWeekText();

        TabHost tabHost = (TabHost) findViewById(R.id.TabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setContent(R.id.월);
        tabSpec.setIndicator("월"); // tab의 lable 값
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setContent(R.id.주);
        tabSpec.setIndicator("주");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab3");
        tabSpec.setContent(R.id.일);
        tabSpec.setIndicator("일");
        tabHost.addTab(tabSpec);

        if(tabSpec.toString().equals("tab1")) database.setTap(0);
        if(tabSpec.toString().equals("tab2")) database.setTap(1);
        if(tabSpec.toString().equals("tab3")) database.setTap(2);

        tabHost.setCurrentTab(database.getTap()); // 실행될 때 처음 보여지는 tabcontent. 0번이 월, 1번이 주, 2번이 일
    }

    public void GotoRegister(View V){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"수정 항목에 진입합니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ScheduleRegister.class);
                startActivity(intent);
            }
        });
    }

    public void prevMonthClick(View V){
        if(cal.getMonth()>1) {
            cal.prevMonth();
            setMonthText();
        }
    }

    public void nextMonthClick(View V){
        if(cal.getMonth()<12) {
            cal.nextMonth();
            setMonthText();
        }
    }
    public void nextDateClick(View V){
        if(cal.getMonth()>=12 && cal.getDate()>=31) return;
        else{
            if((cal.getMonth()==2 && cal.getDate()>=28) || ((cal.getMonth() == 4 || cal.getMonth() == 6 || cal.getMonth() == 9 || cal.getMonth() == 11) && cal.getDate() == 30)) {
                cal.nextMonth();
                cal.setDate(1);
            }
            else if(cal.getDate() == 31){
                cal.nextMonth();
                cal.setDate(1);
            }
        else{
                cal.nextDate();
            }
        }
        setDayText();
    }
    public void prevDateClick(View V) {
        if (cal.getMonth() <= 1 && cal.getDate() <= 1) return;
        else {
            if (cal.getMonth() == 3 && cal.getDate() == 1) {
                cal.prevMonth();
                cal.setDate(28);
            }
            else if((cal.getMonth() == 3 || cal.getMonth() == 5 || cal.getMonth() == 7 || cal.getMonth() == 10 || cal.getMonth() == 12) && cal.getDate() == 1){
                cal.prevMonth();
                cal.setDate(30);
            }
            else if(cal.getDate() == 1){
                cal.prevMonth();
                cal.setDate(31);
            }
            else{
                cal.prevDate();
            }
        }
        setDayText();
    }
    public void prevWeekClick(View V){
        if(cal.getWeek() == 1 && cal.getMonth() == 1) return;
        else if(cal.getWeek() == 1) {
            cal.setWeek(5);
            cal.prevMonth();
        }
        else cal.prevWeek();
        setWeekText();
    }
    public void nextWeekClick(View V){
        if(cal.getWeek() == 5 && cal.getMonth() == 12) return;
        if(cal.getWeek() == 5) {
            cal.setWeek(1);
            cal.nextMonth();
        }
        else cal.nextWeek();
        setWeekText();
    }

    private void setMonthText() {
        TextView tmp = (TextView)findViewById(R.id.monthText);
        tmp.setText("2018년 " + (cal.getMonth()) + "월");
        tmp = (TextView)findViewById(R.id.textMonthView);
        tmp.setText(database.returnMonthData(cal.getMonth()));
    }
    private void setWeekText(){
        TextView tmp = (TextView)findViewById(R.id.weekText);
        tmp.setText((cal.getMonth()) + "월 " + (cal.getWeek()) + "주차");
        tmp = (TextView) findViewById(R.id.monday);
    }
    private void setDayText(){
        TextView tmp = (TextView)findViewById(R.id.DateText);
        tmp.setText("2018년 " + (cal.getMonth()) + "월 " + (cal.getDate()) + "일");
        tmp = (TextView)findViewById(R.id.textDateView);
        tmp.setText(database.returnDayData(cal.getMonth(),cal.getDate()));
    }
}