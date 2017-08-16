package com.example.swordpc07.mywebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


public class StaggerdViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggerd_view);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);

        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        StaggeredViewAdapter rcAdapter = new StaggeredViewAdapter(StaggerdViewActivity.this);
        recyclerView.setAdapter(rcAdapter);


    }
}
