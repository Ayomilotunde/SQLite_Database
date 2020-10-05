package com.ayomi.sqlitedatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> bookID, bookTitle, bookAuthor, bookPages;

    CustomAdapters customAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        bookID = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookPages = new ArrayList<>();

        storeDataInArray();
        customAdapters = new CustomAdapters(MainActivity.this, MainActivity.this, bookID, bookTitle, bookAuthor, bookPages);
        recyclerView.setAdapter(customAdapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor = myDatabaseHelper.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                bookID.add(cursor.getString(0));
                bookTitle.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookPages.add(cursor.getString(3));
            }
        }
    }
}