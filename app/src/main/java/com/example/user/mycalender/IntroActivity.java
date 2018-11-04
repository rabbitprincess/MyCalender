package com.example.user.mycalender;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

public class IntroActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable mRunnable;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        String name = "scheduleDatabase.db";
//        SQLiteDatabase Database = openOrCreateDatabase(name,MODE_WORLD_WRITEABLE,null);
        mRunnable = new Runnable(){
            public void run(){
                Intent intent = new Intent(getApplicationContext(),CalenderActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        };
        handler = new Handler();
        handler.postDelayed(mRunnable, 1300);
    }
    protected void onDestroy(){
        handler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
