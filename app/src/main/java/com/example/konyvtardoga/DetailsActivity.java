package com.example.konyvtardoga;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView titleView = findViewById(R.id.textDetailsTitle);
        TextView authorView = findViewById(R.id.textDetailsAuthor);
        TextView pagesView = findViewById(R.id.textDetailsPages);
        TextView yearView = findViewById(R.id.textDetailsYear);

        Book book = (Book) getIntent().getSerializableExtra("book");

        titleView.setText("Cím: " + book.getTitle());
        authorView.setText("Szerző: " + book.getAuthor());
        pagesView.setText("Oldalszám: " + book.getPages());

        int randomYear = new Random().nextInt(2023 - 1900) + 1900;
        yearView.setText("Évszám: " + randomYear);

        findViewById(R.id.buttonBack).setOnClickListener(v -> finish());
    }
}

