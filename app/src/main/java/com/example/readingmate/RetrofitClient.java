package com.example.readingmate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class RetrofitClient {
    private static final String BASE_URL = "https://openapi.naver.com/v1/";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder() // 레니언트 파싱 (잘못된 형식의 JSON 무시) 수행하는 Gson 객체 생성
                .setLenient()
                .create();

        if (retrofit == null) { // Retrofit 인스턴스가 null이면
            retrofit = new Retrofit.Builder() // 새로운 Retrofit 인스턴스 생성
                    .baseUrl(BASE_URL) // 기본 Url 설정
                    .addConverterFactory(ScalarsConverterFactory.create()) // 응답을 일반 텍스트로 변환
                    .addConverterFactory(GsonConverterFactory.create(gson)) // 제공된 Gson 인스턴스 사용하여 Json 응답 변환
                    .build(); // retrofit 인스턴스 구축되고 'retrofit' 필드에 저장
        }
        return retrofit;
    }

    // 도서 검색 API를 호출하는 메소드
//    public static BookSearchService getBookSearchService() {
//        return getInstance().create(BookSearchService.class);
//    }


}
