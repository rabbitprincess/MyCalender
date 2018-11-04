package com.example.user.mycalender;

import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;


public class myCalendar extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    private int date;
    private int month;
    private int week;
    private int pivotdate;
    private int dayOfWeek;
    public myCalendar(){this.month = cal.get(Calendar.MONTH)+1; this.date = cal.get(Calendar.DATE); this.week = Calendar.WEEK_OF_MONTH;}
    public void setDate(int date){ this.date = date; }
    public void setMonth(int month){ this.month = month; }
    public void setWeek(int week){ this.week = week; }
    public int getDate() { return this.date; }
    public int getMonth() { return this.month; }
    public int getWeek() { return this.week; }
    public void nextMonth(){ this.month++; }
    public void prevMonth(){ this.month--; }
    public void nextDate(){ this.date++; }
    public void prevDate(){ this.date--; }
    public void nextWeek(){ this.week++; }
    public void prevWeek(){ this.week--; }

    }

