package com.example.steph.blijdorp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNormalEntrance(View view) {
        Intent intent1 = new Intent(this, NormalEntranceActivity.class);
        startActivity(intent1);
    }

    public void startFoodEntrance(View view) {
        Intent intent2 = new Intent(this, FoodEntranceActivity.class);
        startActivity(intent2);
    }
}
