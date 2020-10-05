package com.ayomi.sqlitedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtUpdateTitle, edtUpdateAuthor, edtUpdatePage;
    private Button btnUpdateBook, btnDeleteBook;
    String ID, TITLE, AUTHOR, PAGES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtUpdateTitle = findViewById(R.id.edtUpdateTitle);
        edtUpdateAuthor = findViewById(R.id.edtUpdateAuthor);
        edtUpdatePage = findViewById(R.id.edtUpdatePage);
        btnUpdateBook = findViewById(R.id.btnUpdateBook);
        btnDeleteBook = findViewById(R.id.btnDeleteBook);


        // call this first
        getIntentData();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(TITLE);

        btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                // then this(update data parameter)


                TITLE = edtUpdateTitle.getText().toString().trim();
                AUTHOR = edtUpdateAuthor.getText().toString().trim();
                PAGES = edtUpdatePage.getText().toString().trim();
                myDatabaseHelper.updateData(ID, TITLE, AUTHOR, PAGES);
                finish();
            }
        });


        btnDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {


            // GETTING DATA
            ID = getIntent().getStringExtra("id");
            TITLE = getIntent().getStringExtra("title");
            AUTHOR = getIntent().getStringExtra("author");
            PAGES = getIntent().getStringExtra("pages");

            // SETTING DATA
            edtUpdateTitle.setText(TITLE);
            edtUpdateAuthor.setText(AUTHOR);
            edtUpdatePage.setText(PAGES);

        } else {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + TITLE + "?");
        builder.setMessage("Are you sure you want to delete " + TITLE + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                myDatabaseHelper.deleteOneRow(ID);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}