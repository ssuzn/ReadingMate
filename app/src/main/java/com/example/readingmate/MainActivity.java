package com.example.readingmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private String client_id = "Cp9OwhWZRrPtM19C02GB";
    private String client_secret = "ikIuIx6o0U";
    private EditText editTextSearch;
    private String keyword;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTextSearch);
        Button buttonSearch = findViewById(R.id.buttonSearch);
        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰 & 어댑터 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BookItem book) {
                openBookDetail(book);
            }
        }, this);
        recyclerView.setAdapter(bookAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editTextSearch.getText().toString();
                if (!keyword.isEmpty()) {
                    getResultSearch(keyword);
                }
            }
        });
    }

    void getResultSearch(String keyword) {
        BookSearchService bookSearchService = RetrofitClient.getInstance().create(BookSearchService.class);
        Call<BookSearchResponse> call = bookSearchService.searchBooks(client_id, client_secret, keyword);

        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookItem> bookList = response.body().getItems();
                    // 어댑터 데이터 업데이트
                    bookAdapter.submitList(bookList);
                } else {
                    Log.d(TAG, "실패");
                }
            }
            @Override
            public void onFailure(Call<BookSearchResponse> call, Throwable t) {
                Log.d(TAG, "에러: " + t.getMessage());
            }
        });
    }

    private void openBookDetail(BookItem book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("title", book.getTitle());
        intent.putExtra("author", book.getAuthor());
        intent.putExtra("publisher", book.getPublisher());
        intent.putExtra("imageURL", book.getImageURL());
        intent.putExtra("description", book.getDescription());
        startActivity(intent);
    }
}
