package com.ayomi.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText edtTitle, edtAuthor, edtPage;
    private Button btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtPage = findViewById(R.id.edtPage);
        btnAddBook = findViewById(R.id.btnBook);

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddActivity.this);
                myDatabaseHelper.addBook(edtTitle.getText().toString().trim(),
                        edtAuthor.getText().toString().trim(),
                        Integer.valueOf(edtPage.getText().toString().trim()));
            }
        });
    }
}