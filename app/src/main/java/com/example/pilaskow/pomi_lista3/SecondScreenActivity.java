package com.example.pilaskow.pomi_lista3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SecondScreenActivity extends AppCompatActivity {

    String traceback = "traceback";
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        counter = getIntent().getIntExtra(MainActivity.key,-1);
    }

    @Override
    protected void onDestroy() {
        setResult(MainActivity.startActivityRequestCode,new Intent().putExtra(traceback, "activity result   " + counter));
        super.onDestroy();
    }

}
