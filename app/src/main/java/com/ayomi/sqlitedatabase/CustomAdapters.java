package com.ayomi.sqlitedatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapters extends RecyclerView.Adapter<CustomAdapters.MyViewHolder> {

    private View view;
    private Context context;
    Activity activity;

    private ArrayList bookId, bookTitle, bookAuthor, bookPages;


    CustomAdapters(Activity activity, Context context, ArrayList bookId, ArrayList bookTitle, ArrayList bookAuthor, ArrayList bookPages){
        this.activity = activity;
        this.context = context;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.bookId.setText(String.valueOf(bookId.get(position)));
        holder.bookTitle.setText(String.valueOf(bookTitle.get(position)));
        holder.bookAuthor.setText(String.valueOf(bookAuthor.get(position)));
        holder.bookPages.setText(String.valueOf(bookPages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(bookId.get(position)));
                intent.putExtra("title", String.valueOf(bookTitle.get(position)));
                intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
                intent.putExtra("pages", String.valueOf(bookPages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookId, bookTitle, bookAuthor, bookPages;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            bookId = itemView.findViewById(R.id.txtBookID);
            bookTitle = itemView.findViewById(R.id.txtBookTitlle);
            bookAuthor = itemView.findViewById(R.id.txtBookAuthor);
            bookPages = itemView.findViewById(R.id.txtBookPage);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
