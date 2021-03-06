package com.example.steph.blijdorp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainFoodTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food_time);
    }

    public void startBack(View view) {
        Intent intentBack = new Intent(this, FoodEntranceActivity.class);
        startActivity(intentBack);
    }

    public void startRoute0900(View view) {
        Intent intent0900 = new Intent(this, MainFoodRoute0900Activity.class);
        startActivity(intent0900);
    }
}
