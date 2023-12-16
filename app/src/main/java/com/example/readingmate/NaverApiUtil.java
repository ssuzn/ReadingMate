package com.example.readingmate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.readingmate.BookListAdapter;


import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NaverApiUtil {
    private static final String NAVER_API_URL = "https://openapi.naver.com/v1/search/book.json";
    private static final String CLIENT_ID = "Cp9OwhWZRrPtM19C02GB";
    private static final String CLIENT_SECRET = "ikIuIx6o0U";

    // 도서 검색을 위한 AsyncTask
    public static class BookSearchTask extends AsyncTask<String, Void, ArrayList<Book>> {
        private Context context;
        private BookListAdapter adapter;

        public BookSearchTask(Context context, BookListAdapter adapter) {
            this.context = context;
            this.adapter = adapter;
        }

        @Override
        protected ArrayList<Book> doInBackground(String... params) {
            String searchQuery = params[0];
            return searchBooks(searchQuery);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> result) {
            adapter.clear();
            adapter.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }

    // 도서 검색을 위한 메서드
    public static ArrayList<Book> searchBooks(String query) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            // 네이버 API 요청을 위한 URL 생성
            String url = NAVER_API_URL + "?query=" + query;

            // OkHttpClient를 사용하여 네트워크 통신
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("X-Naver-Client-Id", CLIENT_ID)
                    .header("X-Naver-Client-Secret", CLIENT_SECRET)
                    .build();

            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            // JSON 데이터 파싱
            JSONObject jsonResponse = new JSONObject(responseData);
            JSONArray items = jsonResponse.getJSONArray("items");

            // 각 아이템을 Book 객체로 변환하여 리스트에 추가
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String title = item.getString("title");
                String author = item.getString("author");
                String imageUrl = item.getString("image");
                String publisher = item.getString("publisher");
                String description = item.getString("description");

                Book book = new Book(title, author, imageUrl, publisher, description);
                books.add(book);
            }
        } catch (IOException | JSONException e) {
            Log.e("NaverApiUtil", "Error during book search", e);
        }

        return books;
    }

    private static ArrayList<Book> parseBookList(String jsonData) throws JSONException {
        // JSON 데이터를 파싱하여 Book 객체로 변환하는 로직 추가
        ArrayList<Book> books = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray items = jsonObject.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);

            String title = item.getString("title");
            String author = item.getString("author");
            String imageURL = item.getString("imageURL");
            String publisher = item.getString("publisher");
            String publishedDate = item.getString("pubdate");

            Book book = new Book(title, author, imageURL, publisher, publishedDate);
            books.add(book);
        }

        return books;
    }

    // 이미지 로딩을 위한 메서드 (Glide 사용)
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageView);
    }
}
