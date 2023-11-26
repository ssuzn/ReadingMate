package com.example.readingmate;


import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private String client_id = "Cp9OwhWZRrPtM19C02GB";
    private String client_secret = "ikIuIx6o0U";
    private EditText editTextSearch;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSearch = findViewById(R.id.editTextSearch);
        Button buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editTextSearch.getText().toString();
                if (!keyword.isEmpty()) {
                    getResultSearch();
                }
            }
        });
    }

    void getResultSearch() {
        BookSearchService bookSearchService = RetrofitClient.getInstance().create(BookSearchService.class);
        Call<BookSearchResponse> call = bookSearchService.searchBooks(client_id, client_secret, keyword);

        call.enqueue(new Callback<BookSearchResponse>() {
            @Override
            public void onResponse(Call<BookSearchResponse> call, Response<BookSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookItem> bookList = response.body().getItems();
                    for (BookItem bookItem : bookList) {
                        Log.d(TAG, "도서 제목: "  + bookItem.getTitle());
                        Log.d(TAG, "저자: " + bookItem.getAuthor());
                    }
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
}
