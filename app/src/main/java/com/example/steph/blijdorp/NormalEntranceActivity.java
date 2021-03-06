package com.example.steph.blijdorp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NormalEntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_entrance);
    }

    public void startBack(View view) {
        Intent intentBack = new Intent(this, MainActivity.class);
        startActivity(intentBack);
    }

    public void startMainNormalTime(View view) {
        Intent intent1 = new Intent(this, MainNormalTimeActivity.class);
        startActivity(intent1);
    }
}
