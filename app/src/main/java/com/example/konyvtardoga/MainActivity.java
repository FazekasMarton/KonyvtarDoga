package com.example.konyvtardoga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTitle, editAuthor, editPages;
    private ListView listViewBooks;
    private List<Book> bookList;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        findViewById(R.id.buttonAdd).setOnClickListener(v -> addBook());

        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            Book book = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("book", book);
            startActivity(intent);
        });
    }

    private void addBook() {
        String title = editTitle.getText().toString().trim();
        String author = editAuthor.getText().toString().trim();
        String pagesStr = editPages.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
            return;
        }

        int pages = Integer.parseInt(pagesStr);
        if (pages < 50) {
            Toast.makeText(this, "Az oldalszám legalább 50 legyen!", Toast.LENGTH_SHORT).show();
            return;
        }

        bookList.add(new Book(title, author, pages));
        adapter.notifyDataSetChanged();

        editTitle.setText("");
        editAuthor.setText("");
        editPages.setText("");
    }

    public void init(){
        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        editPages = findViewById(R.id.editPages);
        listViewBooks = findViewById(R.id.listViewBooks);

        bookList = new ArrayList<>();
        adapter = new BookAdapter(this, bookList);
        listViewBooks.setAdapter(adapter);
    }
}