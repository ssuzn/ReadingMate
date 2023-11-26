package com.example.readingmate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

// 레트로핏 객체를 통해 호출할 추상 메서드 정의
public interface BookSearchService {
    @GET("search/book.json")
    Call<BookSearchResponse> searchBooks(
            @Header("X-Naver-Client-Id") String clientId,
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query // 검색어
    );
}
