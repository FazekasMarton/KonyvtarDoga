package com.example.konyvtardoga;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        super(context, R.layout.list_item_book, books);
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        }

        Book book = books.get(position);

        TextView titleView = convertView.findViewById(R.id.textTitle);
        TextView authorView = convertView.findViewById(R.id.textAuthor);
        Button deleteButton = convertView.findViewById(R.id.buttonDelete);

        titleView.setText(book.getTitle());
        authorView.setText(book.getAuthor());

        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Megerősítés")
                    .setMessage("Biztosan törölni szeretné?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        });

        return convertView;
    }
}

