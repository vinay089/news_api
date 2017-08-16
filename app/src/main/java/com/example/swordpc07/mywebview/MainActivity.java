package com.example.swordpc07.mywebview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String LOAD_URL_NEWS="https://newsapi.org/v1/sources";

        ProgressBar progressBar= (ProgressBar) findViewById(R.id.progressBar);

        Intent intent = new Intent(MainActivity.this, LoadNewsListService.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", LOAD_URL_NEWS);
        intent.putExtras(bundle);
        startService(intent);

    }
}
