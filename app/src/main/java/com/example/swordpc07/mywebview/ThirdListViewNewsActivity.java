package com.example.swordpc07.mywebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ThirdListViewNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingleSourceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_list_view_news);

        recyclerView= (RecyclerView) findViewById(R.id.mrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter= new SingleSourceAdapter(ThirdListViewNewsActivity.this);

        recyclerView.setAdapter(adapter);

    }
}
