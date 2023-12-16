package com.example.readingmate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BestSellerActivity extends AppCompatActivity {

    private ListView bestSellerListView;
    private BookListAdapter bestSellerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);

        bestSellerListView = findViewById(R.id.listViewBestSellers);
        bestSellerListAdapter = new BookListAdapter(this, new ArrayList<Book>());

        bestSellerListView.setAdapter(bestSellerListAdapter);

        new BestSellerTask().execute();
    }
    private class BestSellerTask extends AsyncTask<Void, Void, ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(Void... voids) {
            return NaverApiUtil.searchBooks("베스트셀러");
        }

        @Override
        protected void onPostExecute(ArrayList<Book> result) {
            bestSellerListAdapter.clear();
            bestSellerListAdapter.addAll(result);
            bestSellerListAdapter.notifyDataSetChanged();
        }
    }
}
