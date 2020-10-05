package com.ayomi.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtUpdateTitle, edtUpdateAuthor, edtUpdatePage;
    private Button btnUpdateBook;
    String ID, TITLE, AUTHOR, PAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtUpdateTitle = findViewById(R.id.edtUpdateTitle);
        edtUpdateAuthor = findViewById(R.id.edtUpdateAuthor);
        edtUpdatePage = findViewById(R.id.edtUpdatePage);
        btnUpdateBook = findViewById(R.id.btnUpdateBook);

        // call this first
        getIntentData();

        btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                // then this(update data parameter)
                myDatabaseHelper.updateData(ID, TITLE, AUTHOR, PAGES);
            }
        });





    }

    void getIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){

            // GETTING DATA
           ID = getIntent().getStringExtra("id");
        TITLE = getIntent().getStringExtra("title");
        AUTHOR = getIntent().getStringExtra("author");
        PAGES = getIntent().getStringExtra("pages");

        // SETTING DATA
            edtUpdateTitle.setText(TITLE);
            edtUpdateAuthor.setText(AUTHOR);
            edtUpdatePage.setText(PAGES);

        }else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}