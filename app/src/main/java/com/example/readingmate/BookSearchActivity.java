package com.example.readingmate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private ListView bookListView;
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        bookListView = findViewById(R.id.listViewBooks);
        bookListAdapter = new BookListAdapter(this, new ArrayList<Book>());

        bookListView.setAdapter(bookListAdapter);

        // 검색 버튼 클릭 이벤트 처리
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextSearch.getText().toString();
                if (!searchQuery.isEmpty()) {
                    new BookSearchTask().execute(searchQuery);
                }
            }
        });

        // 검색된 도서 목록 클릭 이벤트 처리
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book selectedBook = (Book) parent.getItemAtPosition(position);
                showBookDetails(selectedBook);
            }
        });
    }

    private void showBookDetails(Book selectedBook) {
        Intent intent = new Intent(this, BookDetailsActivity.class);
        intent.putExtra("SELECTED_BOOK", selectedBook);
        startActivity(intent);
    }

    private class BookSearchTask extends AsyncTask<String, Void, ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(String... params) {
            // 도서 검색 로직 추가
            String searchQuery = params[0];
            return NaverApiUtil.searchBooks(searchQuery);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> result) {
            // 검색된 도서 목록을 어댑터에 설정하여 표시
            bookListAdapter.clear();
            bookListAdapter.addAll(result);
            bookListAdapter.notifyDataSetChanged();
        }
    }
}
