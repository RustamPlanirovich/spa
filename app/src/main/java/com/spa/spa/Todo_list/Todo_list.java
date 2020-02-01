package com.spa.spa.Todo_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.R;

public class Todo_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        getSupportActionBar().hide();
    }
}
