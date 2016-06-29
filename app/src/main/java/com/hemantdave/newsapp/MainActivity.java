package com.hemantdave.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView stories = (ListView) findViewById(R.id.storiesLV);
        stories.setAdapter(new CustomListViewAdapter(this));

    }
}
