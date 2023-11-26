package com.example.readingmate;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // 전달된 데이터 받기
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String publisher = getIntent().getStringExtra("publisher");
        String imageURL = getIntent().getStringExtra("imageURL");
        String description = getIntent().getStringExtra("description");

        // 화면에 표시
        ImageView imageViewCover = findViewById(R.id.imageViewCover);
        Picasso.get().load(imageURL).placeholder(R.drawable.placeholder_image).into(imageViewCover);

        TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);

        TextView textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewAuthor.setText(author);

        TextView textViewPublisher = findViewById(R.id.textViewPublisher);
        textViewPublisher.setText(publisher);

        TextView textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setText(description);
    }
}